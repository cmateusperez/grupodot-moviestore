package com.grupodot.moviestore.webService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.service.MovieService;

@RestController
public class MovieServiceController {
	
	@Autowired
	private MovieService movieService;

	@RequestMapping(value = "/movie/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Movie> queryMovieByName(@PathVariable String name){
		return movieService.queryMovieByName(name);
	}
	
	@RequestMapping(value = "/addmovie", method = RequestMethod.POST, headers = "Accept=application/json")
	public Movie addMovie(@RequestBody Movie movie) {
		movieService.saveMovie(movie);
		return movie;
	}
}
