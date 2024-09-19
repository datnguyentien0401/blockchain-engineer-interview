package com.example.genomicserver;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Hash;
import org.web3j.crypto.Sign;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.security.Security;
import java.util.Base64;

import com.example.genomicserver.dto.User;
import com.example.genomicserver.service.AuthService;
import com.example.genomicserver.service.BlockchainService;
import com.example.genomicserver.service.StorageService;
import com.example.genomicserver.service.TEEService;

import org.web3j.utils.Numeric;

public final class Integration {

    public static void main(String[] args) {
        try {
            // Initialize
            Security.addProvider(new BouncyCastleProvider());

            final AuthService authService = new AuthService();
            final TEEService teeService = new TEEService();
            final StorageService storageService = new StorageService();

            final Web3j web3 = Web3j.build(new HttpService("https://sepolia.infura.io/v3/8bd78e7286514263a05d3721f9ed8438"));
            final Credentials credentials = Credentials.create("56289e99c94b6912bfc12adc093c9b51124f0dc54ac7a766b2bc5ccf558d8027");
            final ECKeyPair keyPair = credentials.getEcKeyPair();

            final String ethAddress = credentials.getAddress();
            System.out.println("User Address: " + ethAddress);

            final BlockchainService blockchainService = new BlockchainService(web3, credentials);

            //Register user
            final String publicKeyHex = Numeric.toHexStringNoPrefix(keyPair.getPublicKey());
            final long userId = authService.createUser(publicKeyHex);
            System.out.println("Register user success - userId: " + userId);

            //Verify authentication
            final boolean authenticated = authService.authenticate(userId, ethAddress);
            if (!authenticated) {
                System.out.println("Authentication failed");
                return;
            }
            System.out.println("Authentication success");

            //Get user
            final User user = authService.getUser(userId);

            //Encrypt gene data
            final String geneData = "geneTestData";
            final byte[] encryptedGene = teeService.encrypt(user.getPublicKey(), geneData);
            System.out.println("Encrypted gene data: " +  Base64.getEncoder().encodeToString(encryptedGene));

            //Sign encrypted data
            final byte[] geneDataHash = Hash.sha256(encryptedGene);
            // Sign the hash
            final Sign.SignatureData signature = Sign.signMessage(geneDataHash, credentials.getEcKeyPair());
            final byte[] signatureBytes = new byte[65];
            System.arraycopy(signature.getR(), 0, signatureBytes, 0, 32);
            System.arraycopy(signature.getS(), 0, signatureBytes, 32, 32);
            signatureBytes[64] = signature.getV()[0];

            final String geneDataHashBase64 = Base64.getEncoder().encodeToString(geneDataHash);
            final String signatureBase64 = Base64.getEncoder().encodeToString(signatureBytes);
            System.out.println("Content Hash: " + geneDataHashBase64);
            System.out.println("Signature: " + signatureBase64);

            //Save encrypted gene into storage
            final String docId = storageService.saveGene(userId, encryptedGene, signatureBytes, geneDataHash);
            System.out.println("Save gene success - docId: " + docId);

            //verify signature
            boolean validSignature = storageService.verifySignature(docId, user.getPublicKey());
            System.out.println("Signature valid: " + validSignature);

            //Upload data to blockchain
            final String transactionHash = blockchainService.uploadData(docId);
            System.out.println("Uploaded data to blockchain success - Transaction hash: " + transactionHash);

            final BigInteger sessionId = blockchainService.handleUploadDataEvent(docId);
            System.out.println("SessionId: " + sessionId);

            //Calculate risk score
            final int riskScore = teeService.calculateRiskCore(geneData);
            System.out.println("Risk score: " + riskScore);

            //Confirm
            blockchainService.confirm(docId, geneDataHashBase64, signatureBase64, sessionId, BigInteger.valueOf(riskScore));
            System.out.println("Confirm transaction");

            //verify signature
            validSignature = storageService.verifySignature(docId, user.getPublicKey());
            System.out.println("Signature valid: " + validSignature);

            //Retrieve encrypted data
            final byte[] geneEncryptedData = storageService.getGeneEncryptedData(docId);
            System.out.println("Retrieve encrypted data success");

            //Decrypt gene data
            final String decryptGeneData = teeService.decryptGeneData(keyPair.getPrivateKey(), geneEncryptedData);
            System.out.println("Decrypted data success: " + decryptGeneData);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

