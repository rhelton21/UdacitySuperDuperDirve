package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignupController {

    @Autowired
    private UserService userService;

    @Autowired
    private CredentialService credentialService;

    @GetMapping("/signup")
    String getSignup() { return "signup";}

    @PostMapping("/signup")
    String postSignup(@ModelAttribute User user, Model model){
        String signupError = null;

        if (!userService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (signupError == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
            return "login";
        } else {
            model.addAttribute("signupError", signupError);
            return "signup";
        }
    }

    @PostMapping("/cred")
    String create(Model model, @ModelAttribute("newCred") Credential credential, Authentication auth) {
        credential.setUserid(this.userService.getUser(auth.getName()).getUserid());
        int result = this.credentialService.createOrUpdate(credential);
        if (result > 0) model.addAttribute("saved", true);
        else model.addAttribute("saved", false);
        return "result";
    }

    @GetMapping("/deletecred/{id}")
    String delete(Model model, @PathVariable("id") Integer credentialid) {
        this.credentialService.deleteCredential(credentialid);
        model.addAttribute("saved", true);
        return "result";
    }
}
