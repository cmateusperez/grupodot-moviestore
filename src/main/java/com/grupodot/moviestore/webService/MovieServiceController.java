package com.grupodot.moviestore.webService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.service.MovieService;



@RestController
public class MovieServiceController {
	
	@Autowired
	private MovieService movieService;

	@RequestMapping( value="/movie/{id}",  method = RequestMethod.GET)
	public Movie ObtemerMovie(@RequestParam(value="id", required = true) int id){
		return movieService.queryMovieById(id);
	}
	
	
}
