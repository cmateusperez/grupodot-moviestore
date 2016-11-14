package com.grupodot.moviestore.dao;

import java.util.List;

import com.grupodot.moviestore.entities.Movie;

public interface MovieDao {
	
	public void saveMovie(Movie movie);
	
	public List<Movie> queryAllMovies();

}
