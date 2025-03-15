package dev.aamgasi.MovieWizard.Data.Controllers;

import dev.aamgasi.MovieWizard.Data.Movies.Movie;
import dev.aamgasi.MovieWizard.Data.Movies.MovieService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/all")
    public ResponseEntity<List<Movie>> getAllMovies(){
        return new ResponseEntity<>(movieService.allMovies(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Movie>> getSingleMovie(@PathVariable ObjectId id){
        return new ResponseEntity<>(movieService.getSingle(id),HttpStatus.OK);
    }
    @GetMapping("/genre")
    public ResponseEntity<Optional<List<Movie>>> getGenre(@RequestParam String genre){
        return new ResponseEntity<>(movieService.getGenre(genre),HttpStatus.OK);
    }
    @GetMapping("/random/10")
    public ResponseEntity<List<Movie>> get10FromGenre(@RequestParam String genre) {
        return new ResponseEntity<>(movieService.getRandom10FromGenre(genre), HttpStatus.OK);
    }

}
