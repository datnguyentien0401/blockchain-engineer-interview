package com.example.genomicserver.service;

import org.springframework.stereotype.Service;

import org.web3j.abi.EventEncoder;
import org.web3j.abi.FunctionReturnDecoder;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.DefaultBlockParameterNumber;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.EthLog.LogResult;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.core.methods.response.EthGetTransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.example.genomicserver.contract.Controller;

@Service
public class BlockchainService {
    private static final String WALLET_ADDRESS = "0x8db97C7cEcE249c2b98bDC0226Cc4C2A57BF52FC";

    private final Web3j web3j;
    private final Controller controller;

    public static final Event UPLOAD_DATA_EVENT = new Event(
            "UploadData",
            Arrays.asList(
                    new org.web3j.abi.TypeReference<Utf8String>() {}, // docId
                    new org.web3j.abi.TypeReference<Uint256>() {} // sessionId
            )
    );

    public BlockchainService(Web3j web3j, Credentials credentials) throws Exception {
        this.web3j = web3j;
        controller = Controller.load(
                WALLET_ADDRESS,
                web3j,
                credentials,
                new DefaultGasProvider()
        );
    }

    public String uploadData(String docId) throws Exception {
        final TransactionReceipt transactionResponse = controller.uploadData(docId).send();
        final String transactionHash = transactionResponse.getTransactionHash();
        waitForTransactionReceipt(transactionHash);

        return transactionHash;
    }

    public void confirm(String docId, String contentHash, String proof, BigInteger sessionId, BigInteger riskScore) throws Exception {
        final TransactionReceipt transactionResponse = controller.confirm(docId, contentHash, proof, sessionId, riskScore).send();
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
                    throw new RuntimeException("Transaction failed: " + receipt.getTransactionReceipt().get().getStatus());
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

        // Create a filter using the fromBlock and the latestBlock
        final EthFilter filter = new EthFilter(
                new DefaultBlockParameterNumber(fromBlock),
                new DefaultBlockParameterNumber(latestBlock),
                WALLET_ADDRESS
        );

        final String encodedEventSignature = EventEncoder.encode(UPLOAD_DATA_EVENT);
        filter.addSingleTopic(encodedEventSignature);

        final List<LogResult> logs = web3j.ethGetLogs(filter).send().getLogs();
        for (var logResult : logs) {
            final Log log = (Log) logResult.get();
            final List<Type> eventValues = FunctionReturnDecoder.decode(
                    log.getData(), UPLOAD_DATA_EVENT.getParameters()
            );

            if (eventValues.size() == 2) {
                final Utf8String docId = (Utf8String) eventValues.get(0);
                final Uint256 sessionId = (Uint256) eventValues.get(1);

                if (docId.getValue().equals(fileId)) {
                    System.out.printf("Found Event - DocId: %s - SessionId: %s%n", docId.getValue(), sessionId.getValue().toString());
                    return sessionId.getValue();
                }
            }
        }
        throw new Exception("Event not found");
    }
}

