package com.example.genomicserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gene {
    private Long userId;
    private String fileId;
    private byte[] hash;
    private byte[] encryptedData;
    private byte[] signature;
}
