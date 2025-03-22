package dev.aamgasi.MovieWizard.Data.Movies;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepo repo;

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Movie> allMovies(){
        return repo.findAll();
    }

    public Optional<Movie> getSingle(ObjectId id){
        return repo.findById(id);
    }
    //Za pomocą Query w MovieRepo
    public Optional<List<Movie>> getGenre(String genre) {
        return repo.findByGenre(genre);
    }

    public Optional<List<Movie>> getTitle(String title) {return repo.findByTitle(title);}

    //Za pomocą agregacji i mongoTemplate
    public List<Movie> getRandom10FromGenre(String genre) {
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.match(Criteria.where("genres").in(genre)),
                Aggregation.sample(10)
        );

        AggregationResults<Movie> results = mongoTemplate.aggregate(aggregation, Movie.class, Movie.class);
        return results.getMappedResults();
    }

}
