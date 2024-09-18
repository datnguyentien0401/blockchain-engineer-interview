package com.example.genomicserver.service;

import org.springframework.stereotype.Service;
import org.bouncycastle.util.encoders.Hex;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Sign;
import org.web3j.crypto.Sign.SignatureData;

import java.security.SignatureException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import com.example.genomicserver.dto.Gene;

@Service
public class StorageService {

    private final Map<String, Gene> geneMap = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();;

    private static final Integer SIGNATURE_LENGTH = 65;

    public String saveGene(long userId, byte[] encryptedData, byte[] signature, byte[] hash)
            throws Exception {
        if (signature.length != SIGNATURE_LENGTH) {
            throw new IllegalArgumentException("Signature invalid");
        }

        final String fileId = Hex.toHexString(hash).substring(0, 16);

        lock.lock();
        try {
            if (geneMap.containsKey(fileId)) {
                throw new Exception("Gene already exists");
            }
            geneMap.put(fileId, new Gene(userId, fileId, hash, encryptedData, signature));

            return fileId;
        } finally {
            lock.unlock();
        }
    }

    private Gene getGene(String fileId) throws Exception {
        if (!geneMap.containsKey(fileId)) {
            throw new Exception("Gene not found");
        }

        final Gene gene = geneMap.get(fileId);
        if (Objects.isNull(gene)) {
            throw new Exception("Gene not found");
        }
        return gene;
    }

    public byte[] getGeneEncryptedData(String fileId) throws Exception {
        lock.lock();
        try {
            return getGene(fileId).getEncryptedData();
        } finally {
            lock.unlock();
        }
    }

    private static SignatureData toSignatureData(byte[] signature) {
        final byte[] r = Arrays.copyOfRange(signature, 0, 32);      // First 32 bytes
        final byte[] s = Arrays.copyOfRange(signature, 32, 64);     // Next 32 bytes
        final byte v = signature[64];                               // Last byte

        return new Sign.SignatureData(v, r, s);
    }

    public boolean verifySignature(String fileId, ECKeyPair keyPair) throws Exception {
        lock.lock();
        try {
            final var gene = getGene(fileId);

            final byte[] signatureNoRecoverId = new byte[64];
            System.arraycopy(gene.getSignature(), 0, signatureNoRecoverId, 0, 64);

            return Sign.signedMessageToKey(gene.getHash(), toSignatureData(gene.getSignature()))
                       .equals(keyPair.getPublicKey());

        } catch (SignatureException | CipherException e) {
            throw new Exception("Verify signature failed", e);
        } finally {
            lock.unlock();
        }
    }
}

