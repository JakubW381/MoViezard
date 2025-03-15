package dev.aamgasi.MovieWizard.Data.Movies;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepo extends MongoRepository<Movie, ObjectId> {

    @Query("{ 'genres' : { '$in' : [?0] } }")
    Optional<List<Movie>> findByGenre(String genre);

}
