package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.domain.UserCredential;
import za.ac.cput.service.UserCredentialService;

@RestController
@RequestMapping("/usercredential")
public class UserCredentialController {

    @Autowired
    private UserCredentialService service;

    @PostMapping("/register")
    public UserCredential register(@RequestBody UserCredential user) {
        return service.register(user);
    }
}
