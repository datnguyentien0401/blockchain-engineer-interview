package com.example.genomicserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class Web3jConfig {
    private final Web3jConfigProperties web3jConfigProperties;
    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService(web3jConfigProperties.getNodeUrl()));
    }
    @Bean
    public Credentials credentials() throws Exception {
        return Credentials.create(web3jConfigProperties.getPrivateKey());
    }
}
