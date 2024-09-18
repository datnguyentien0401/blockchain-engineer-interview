package com.example.genomicserver.service;

import org.springframework.stereotype.Service;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;
import org.web3j.tx.gas.StaticGasProvider;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import com.example.genomicserver.contract.Controller;
import com.example.genomicserver.contract.Controller.UploadDataEventResponse;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;

@Service
public class BlockchainService {
    private static final String WALLET_ADDRESS = "0x8db97C7cEcE249c2b98bDC0226Cc4C2A57BF52FC";

    private final Web3j web3j;
    private final Controller controller;

    public BlockchainService(Web3j web3j, Credentials credentials) throws Exception {
        this.web3j = web3j;
        final RawTransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials,
                9999L
        );
        final BigInteger currentGasPrice = web3j.ethGasPrice().send().getGasPrice();
        final StaticGasProvider gasProvider = new StaticGasProvider(currentGasPrice,
                                                                    DefaultGasProvider.GAS_LIMIT);
        controller = Controller.load(
                WALLET_ADDRESS,
                web3j,
                transactionManager,
                gasProvider
        );
    }

    public String uploadData(String docId) throws Exception {
        final TransactionReceipt transactionResponse = controller.uploadData(docId).send();
        final String transactionHash = transactionResponse.getTransactionHash();
        waitForTransactionReceipt(transactionHash);

        return transactionHash;
    }

    public void confirm(String docId, String contentHash, String proof, BigInteger sessionId,
                        BigInteger riskScore) throws Exception {
        final TransactionReceipt transactionResponse = controller.confirm(docId, contentHash, proof, sessionId,
                                                                          riskScore).send();
        final String transactionHash = transactionResponse.getTransactionHash();
        waitForTransactionReceipt(transactionHash);
    }

    private void waitForTransactionReceipt(String transactionHash)
            throws InterruptedException, ExecutionException, IOException {
        // Poll for the transaction receipt
        while (true) {
            final EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(transactionHash).send();
            if (receipt.getTransactionReceipt().isPresent()) {
                if (receipt.getTransactionReceipt().get().isStatusOK()) {
                    break;
                } else {
                    throw new RuntimeException(
                            "Transaction failed: " + receipt.getTransactionReceipt().get().getStatus());
                }
            }
            Thread.sleep(1000);
        }
    }

    public BigInteger handleUploadDataEvent(String fileId) throws Exception {
        final CountDownLatch latch = new CountDownLatch(1);
        final BigInteger latestBlock = web3j.ethBlockNumber().send().getBlockNumber();

        BigInteger fromBlock = latestBlock.subtract(BigInteger.TEN);
        if (fromBlock.signum() < 0) {
            fromBlock = BigInteger.ZERO;
        }

        final Flowable<UploadDataEventResponse> uploadDataEventResponseFlowable = controller.uploadDataEventFlowable(
                new DefaultBlockParameterNumber(fromBlock), new DefaultBlockParameterNumber(latestBlock));

        final AtomicReference<BigInteger> sessionId = new AtomicReference<>();

        uploadDataEventResponseFlowable.subscribeOn(Schedulers.io()).subscribe(eventResponse -> {
            final String docId = eventResponse.docId;
            if (docId.equals(fileId)) {
                System.out.println("UploadData Event - docId: " + docId + ", sessionId: " + eventResponse.sessionId);
                sessionId.set(eventResponse.sessionId);
            }
            latch.countDown();
        }, error -> {
            latch.countDown();
            throw new Exception("Error occurred while listening for events: " + error.getMessage());
        });
        latch.await(1, TimeUnit.MINUTES);
        return sessionId.get();
    }
}

