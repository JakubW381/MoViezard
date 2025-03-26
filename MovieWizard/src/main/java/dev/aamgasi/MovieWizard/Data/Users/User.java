package dev.aamgasi.MovieWizard.Data.Users;

import dev.aamgasi.MovieWizard.Data.Movies.Movie;
import dev.aamgasi.MovieWizard.Data.Reviews.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    private String id = ObjectId.get().toHexString();
    @Field("username")
    private String username;
    @Field("email")
    private String email;
    @Field("password")
    private String password;

    private String oauth2Id;

    private List<Review> reviews;

    private List<Movie> favorites;

    private List<Movie> toWatch;
}
