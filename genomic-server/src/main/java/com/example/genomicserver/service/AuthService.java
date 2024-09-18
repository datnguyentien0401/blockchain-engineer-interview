package com.example.genomicserver.service;

import org.springframework.stereotype.Service;

import org.web3j.crypto.ECKeyPair;
import org.web3j.crypto.Keys;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

import com.example.genomicserver.dto.User;

@Service
public class AuthService {
    private final Map<Long, User> userMap = new HashMap<>();
    private final ReentrantLock lock = new ReentrantLock();

    public Long createUser(String publicKey) {
        lock.lock();
        try {
            final Long userId = (long) (Math.random() * 1_000_000_000L);
            userMap.put(userId, new User(userId, publicKey));
            return userId;
        } finally {
            lock.unlock();
        }
    }

    public boolean authenticate(Long userId, String userAddress) {
        if (!Numeric.containsHexPrefix(userAddress)) {
            return false;
        }

        lock.lock();
        try {
            if (!userMap.containsKey(userId)) {
                return false;
            }

            final User user = userMap.get(userId);
            if (Objects.isNull(user)) {
                return false;
            }

            final BigInteger publicKeyBigInt = new BigInteger(user.getPublicKey(), 16);
            final ECKeyPair ecKeyPair = new ECKeyPair(BigInteger.ZERO, publicKeyBigInt);
            final String address = String.format("0x%s", Keys.getAddress(ecKeyPair.getPublicKey()));

            return userAddress.equalsIgnoreCase(address);
        } finally {
            lock.unlock();
        }
    }

    public User getUser(Long userId) throws Exception{
        lock.lock();
        try {
            if (!userMap.containsKey(userId)) {
                throw new Exception("User not found");
            }

            final User user = userMap.get(userId);
            if (Objects.isNull(user)) {
                throw new Exception("User not found");
            }
            return user;
        } finally {
            lock.unlock();
        }
    }
}

