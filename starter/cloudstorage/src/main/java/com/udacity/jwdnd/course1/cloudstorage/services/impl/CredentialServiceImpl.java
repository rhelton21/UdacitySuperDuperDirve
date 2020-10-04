package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HashService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public class CredentialServiceImpl implements CredentialService {

    private CredentialMapper credentialMapper;

    @Autowired
    private EncryptionService encryptionService;
    
    public CredentialServiceImpl(CredentialMapper credentialMapper) {
        this.credentialMapper = credentialMapper;
    }

    @Override
    public int createOrUpdate(Credential credential){
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        if (credential.getCredentialid() != null) return this.credentialMapper.update(credential);
        else return this.credentialMapper.insert(credential);
    }

    @Override
    public List<Credential> getUserCredentials(User user){
        return this.credentialMapper.findCredentials(user.getUserid());
    }

    @Override
    public String showOne(Integer credentialid){
        Credential cred = this.credentialMapper.findCredential(credentialid);
        String decryptedValue = this.encryptionService.decryptValue(cred.getPassword(),cred.getKey());
        return decryptedValue;
    }

    @Override
    public void deleteCredential(Integer credentialid) {
        this.credentialMapper.deleteCredential(credentialid);
    }
}
