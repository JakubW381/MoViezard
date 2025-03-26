package dev.aamgasi.MovieWizard.Data.Movies;

import dev.aamgasi.MovieWizard.Data.Reviews.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "movies")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    @Id
    private String id = ObjectId.get().toHexString();

    private String title;

    private String year;

    private List<String> genres;

    private String description;

    private String image;

    private String trailer;

    @DocumentReference
    private List<Review> reviews;

}
