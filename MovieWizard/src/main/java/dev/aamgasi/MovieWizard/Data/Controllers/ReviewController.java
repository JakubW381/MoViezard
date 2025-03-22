package dev.aamgasi.MovieWizard.Data.Controllers;

import dev.aamgasi.MovieWizard.Data.Reviews.Review;
import dev.aamgasi.MovieWizard.Data.Reviews.ReviewService;
import dev.aamgasi.MovieWizard.Data.Users.User;
import dev.aamgasi.MovieWizard.Data.Users.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.Authenticator;
import java.util.Map;

@RestController
@RequestMapping("/api/movies")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/{movieId}/add-review")
    public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload, @PathVariable String movieId){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        ObjectId userId = userRepo.findByUsername(username)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new ResponseEntity<>(reviewService.createReview(payload.get("body"), movieId,userId.toString()), HttpStatus.OK);
    }
}
