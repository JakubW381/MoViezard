package dev.aamgasi.MovieWizard.Data.Users;

import dev.aamgasi.MovieWizard.Data.Movies.Movie;
import dev.aamgasi.MovieWizard.Data.Reviews.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private ObjectId id;

    private String username;

    private String password;

    private List<Review> reviews;

    private List<Movie> favorites;

    private List<Movie> toWatch;
}
