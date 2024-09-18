package com.example.genomicserver.service;

import org.springframework.stereotype.Service;
import org.web3j.crypto.ECKeyPair;
import org.web3j.utils.Numeric;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Arrays;

@Service
public class TEEService {
    private static final int GCM_NONCE_LENGTH = 12;

    public int calculateRiskCore(String geneData) throws NoSuchAlgorithmException {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        final byte[] hash = digest.digest(geneData.getBytes(StandardCharsets.UTF_8));

        final BigInteger hashInt = new BigInteger(1, hash);

        final BigInteger riskScore = hashInt.mod(BigInteger.valueOf(4)).add(BigInteger.ONE);
        return riskScore.intValue();
    }

    public byte[] encrypt(String publicKey, String geneData) throws Exception {
        final byte[] sharedSecret = generateSharedSecret(Numeric.toBigInt(publicKey));
        return encryptAES(sharedSecret, geneData.getBytes(StandardCharsets.UTF_8));
    }

    private static byte[] generateSharedSecret(BigInteger publicKey) throws NoSuchAlgorithmException {
        final byte[] bytes = publicKey.toByteArray();
        final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
        return sha256.digest(bytes);
    }

    private static byte[] encryptAES(byte[] key, byte[] plaintext) throws Exception {
        final KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        final SecretKey secretKey = new SecretKeySpec(key, 0, 32, "AES");

        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        final byte[] nonce = new byte[12]; // 12-byte nonce for GCM
        final SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(nonce);

        final GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, nonce); // 128-bit auth tag length
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);

        final byte[] encryptedData = cipher.doFinal(plaintext);

        // Prepend nonce to the encrypted data
        final byte[] cipherTextWithNonce = new byte[nonce.length + encryptedData.length];
        System.arraycopy(nonce, 0, cipherTextWithNonce, 0, nonce.length);
        System.arraycopy(encryptedData, 0, cipherTextWithNonce, nonce.length, encryptedData.length);

        return cipherTextWithNonce;
    }

    public static String decryptGeneData(BigInteger privateKey, byte[] encryptedData) throws Exception {
        // Extract the nonce and ciphertext
        if (encryptedData.length < GCM_NONCE_LENGTH) {
            throw new IllegalArgumentException("Invalid encrypted data");
        }

        final byte[] nonce = Arrays.copyOfRange(encryptedData, 0, GCM_NONCE_LENGTH);
        final byte[] ciphertext = Arrays.copyOfRange(encryptedData, GCM_NONCE_LENGTH, encryptedData.length);

        final byte[] sharedSecret = generateSharedSecret(ECKeyPair.create(privateKey).getPublicKey());

        final SecretKeySpec keySpec = new SecretKeySpec(sharedSecret, 0, 32, "AES");
        final Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");

        final GCMParameterSpec gcmSpec = new GCMParameterSpec(128, nonce);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec);

        final byte[] plaintext = cipher.doFinal(ciphertext);

        return new String(plaintext, StandardCharsets.UTF_8);
    }
}

