package com.example.genomicserver.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.genomicserver.dto.RegisterDto;
import com.example.genomicserver.dto.User;
import com.example.genomicserver.service.AuthService;
import com.example.genomicserver.service.StorageService;
import com.example.genomicserver.service.TEEService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@Validated
@RequiredArgsConstructor
public class UserController {
    private final AuthService authService;
    private final StorageService storageService;
    private final TEEService teeService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authService.createUser(registerDto.getPublicKey()));
    }

//    @GetMapping("/{id}/gene")
//    public ResponseEntity<String> retrieveGeneData(@PathVariable Long id, @RequestParam String fileId) throws Exception {
//        User user = authService.getUser(id);
//        final boolean valid = storageService.verifySignature(fileId, user.getPublicKey());
//        //Retrieve encrypted data
//        final byte[] geneEncryptedData = storageService.getGeneEncryptedData(fileId);
//        //Decrypt gene data
//        final String decryptGeneData = teeService.decryptGeneData(keyPair.getPrivateKey(), geneEncryptedData);
//        System.out.println("Decrypted data success: " + decryptGeneData);
//        return ResponseEntity.ok(decryptGeneData));
//    }
}
