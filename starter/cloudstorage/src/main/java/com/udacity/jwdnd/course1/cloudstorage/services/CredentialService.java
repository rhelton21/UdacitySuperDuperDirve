package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;

@Service
public interface CredentialService {

    public int createOrUpdate(Credential credential);

    public List<Credential> getUserCredentials(User user);

    public String showOne(Integer credentialid);

    public void deleteCredential(Integer credentialid);

}