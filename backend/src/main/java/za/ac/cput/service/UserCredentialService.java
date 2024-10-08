package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.UserCredential;
import za.ac.cput.domain.UserPrincipal;
import za.ac.cput.repository.UserCredentialRepository;

@Service
public class UserCredentialService  {

    @Autowired
    private UserCredentialRepository repository;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserCredential register (UserCredential user)
    {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return  repository.save(user);
    }

    public String verify(UserCredential user) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        }
        return "failure";
    }
}
