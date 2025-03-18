package dev.aamgasi.MovieWizard.Data.Users;

import dev.aamgasi.MovieWizard.Data.Reviews.Review;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        if (userRepo.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists!");
        }
        user.setPassword(encoder.encode(user.getPassword()));
        user.setFavorites(Collections.emptyList());
        user.setReviews(Collections.emptyList());
        user.setToWatch(Collections.emptyList());
        return userRepo.save(user);
    }
}
