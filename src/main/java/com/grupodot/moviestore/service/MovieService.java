package com.grupodot.moviestore.service;

import java.util.List;

import com.grupodot.moviestore.entities.Movie;


public interface MovieService {
	
	public void saveMovie(Movie movie);
	
	public List<Movie> queryAllMovies();
	
	public void queryMovieWithPicture(Movie movie);
	
	public Movie queryMovieById(Integer id);
	
	public List<Movie> queryMovieByName(String name);


}
