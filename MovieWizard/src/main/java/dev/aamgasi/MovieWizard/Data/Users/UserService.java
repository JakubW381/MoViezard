package dev.aamgasi.MovieWizard.Data.Users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder encoder;

    public User registerUser(User user){
        if((user.getEmail().isEmpty() || user.getEmail() == null) || (user.getPassword().isEmpty() || user.getPassword() == null) || (user.getUsername().isEmpty() || user.getUsername() == null)){
            throw new RuntimeException("Missing Credentials");
        }
        if (userRepo.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("User already exists!");
        }
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already in use!");
        }
        user.setUsername(user.getUsername());
        user.setEmail(user.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setOauth2Id("");
        user.setFavorites(Collections.emptyList());
        user.setReviews(Collections.emptyList());
        user.setToWatch(Collections.emptyList());
        return userRepo.save(user);
    }
}
