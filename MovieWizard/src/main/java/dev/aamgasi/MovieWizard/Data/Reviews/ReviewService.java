package dev.aamgasi.MovieWizard.Data.Reviews;

import dev.aamgasi.MovieWizard.Data.Movies.Movie;
import dev.aamgasi.MovieWizard.Data.Users.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepo repo;

    @Autowired
    private MongoTemplate template;

    public Review createReview(String body, String movieId, String userId) {
        String movieObjectId = new ObjectId(movieId).toHexString();
        String userObjectId = new ObjectId(userId).toHexString();

        Review review = new Review(body,movieObjectId,userObjectId);
        repo.save(review);
        template.update(Movie.class)
                .matching(Criteria.where("_id").is(movieObjectId)) // Poprawka na ObjectId
                .apply(new Update().push("reviews").value(review))
                .first();

        template.update(User.class)
                .matching(Criteria.where("_id").is(userObjectId)) // Poprawka na ObjectId
                .apply(new Update().push("reviews").value(review))
                .first();

        return review;
    }
}
