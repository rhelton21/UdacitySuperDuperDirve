package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private FileService fileService;
    @Autowired
    private UserService userService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private EncryptionService encryptionService;


    @GetMapping("/home")
    String getHome(Authentication auth, Model model, @ModelAttribute("newNote")Note note, @ModelAttribute("newCred")Credential cred) {
        User user  = this.userService.getUser(auth.getName());
        List<File>  files = this.fileService.allUserFiles(user);
        List<Note> notes = this.noteService.allUserNotes(user);
        List<Credential> creds = this.credentialService.getUserCredentials(user);
        HashMap<Integer,String> hashMap = new HashMap<>();
        for (Credential credit: creds) {
            hashMap.put(credit.getCredentialid(),this.credentialService.showOne(credit.getCredentialid()));
        }
        model.addAttribute("filelist", files);
        model.addAttribute("notelist", notes);
        model.addAttribute("credlist", creds);
        model.addAttribute("unencrypted", hashMap);
        return "home";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "login";
    }

    @GetMapping("/")
    public String getLHome() {
        return "login";
    }

}
