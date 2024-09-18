package com.example.genomicserver.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "web3j")
@Data
@Component
public class Web3jConfigProperties {
    private String privateKey;
    private String nodeUrl;
}
