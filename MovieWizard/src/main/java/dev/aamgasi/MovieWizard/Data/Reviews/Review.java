package dev.aamgasi.MovieWizard.Data.Reviews;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "reviews")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
    @Id
    private String id = ObjectId.get().toHexString();

    private String userId = ObjectId.get().toHexString();
    private String movieId =ObjectId.get().toHexString();

    private String body;

    public Review(String body, String userId, String movieId) {
        this.body = body;
        this.movieId = movieId;
        this.userId = userId;
    }
}
