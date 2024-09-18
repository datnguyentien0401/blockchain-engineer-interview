package com.example.genomicserver.service;

import org.springframework.stereotype.Service;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
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
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

import com.example.genomicserver.contract.Controller;
import com.example.genomicserver.contract.Controller.UploadDataEventResponse;

import io.reactivex.Flowable;

@Service
public class BlockchainService {
    private static final String WALLET_ADDRESS = "0x8db97c7cece249c2b98bdc0226cc4c2a57bf52fc";
    private final Web3j web3j;
    private final Controller controller;

    public static final Event UPLOADDATA_EVENT = new Event("UploadData",
                                                           Arrays.<TypeReference<?>>asList(
                                                                   new TypeReference<Utf8String>() {},
                                                                   new TypeReference<Uint256>() {}));

    public BlockchainService(Web3j web3j, Credentials credentials) throws Exception {
        this.web3j = web3j;
        final RawTransactionManager transactionManager = new RawTransactionManager(
                web3j,
                credentials,
                43113L
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
        final BigInteger latestBlock = web3j.ethBlockNumber().send().getBlockNumber();

        BigInteger fromBlock = latestBlock.subtract(BigInteger.TEN);
        if (fromBlock.signum() < 0) {
            fromBlock = BigInteger.ZERO;
        }
        final Flowable<UploadDataEventResponse> uploadDataEventResponseFlowable = controller.uploadDataEventFlowable(
                new DefaultBlockParameterNumber(fromBlock), new DefaultBlockParameterNumber(latestBlock));

        final AtomicReference<BigInteger> sessionId = new AtomicReference<>();

        uploadDataEventResponseFlowable.subscribe(eventResponse -> {
            final String docId = eventResponse.docId;
            if (docId.equals(fileId)) {
                System.out.println("UploadData Event - docId: " + docId + ", sessionId: " + eventResponse.sessionId);
                sessionId.set(eventResponse.sessionId);
            }
        }, error -> {
            throw new Exception("Error occurred while listening for events: " + error.getMessage());
        });
        return sessionId.get();

//        final EthFilter filter = new EthFilter(
//                DefaultBlockParameterName.EARLIEST,
//                DefaultBlockParameterName.LATEST,
//                WALLET_ADDRESS
//        );
//
//        final String encodedEventSignature = EventEncoder.encode(UPLOADDATA_EVENT);
//        filter.addSingleTopic(encodedEventSignature);
//
//        final List<LogResult> logs = web3j.ethGetLogs(filter).send().getLogs();
//        for (var logResult : logs) {
//            final Log log = (Log) logResult.get();
//            final List<Type> eventValues = FunctionReturnDecoder.decode(
//                    log.getData(), UPLOADDATA_EVENT.getParameters()
//            );
//
//            if (eventValues.size() == 2) {
//                final Utf8String docId = (Utf8String) eventValues.get(0);
//                final Uint256 sessionId = (Uint256) eventValues.get(1);
//
//                if (docId.getValue().equals(fileId)) {
//                    System.out.printf("Found Event - DocId: %s - SessionId: %s%n", docId.getValue(), sessionId.getValue().toString());
//                    return sessionId.getValue();
//                }
//            }
//        }
//        return null;
    }
}

