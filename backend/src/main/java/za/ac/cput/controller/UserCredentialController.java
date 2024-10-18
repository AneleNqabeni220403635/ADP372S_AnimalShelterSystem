package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.UserCredential;
import za.ac.cput.service.UserCredentialService;

import java.util.List;

@RestController
@RequestMapping("/usercredential")
public class UserCredentialController {

    @Autowired
    private UserCredentialService service;

    @PostMapping("/register")
    public UserCredential register(@RequestBody UserCredential user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserCredential user){

        return service.verify(user);
    }

    @GetMapping("/listUsernames")
    public List<String> listUsernames()
    {
        return service.listUsernames();
    }
}
