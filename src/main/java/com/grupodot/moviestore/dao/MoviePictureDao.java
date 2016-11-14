package com.grupodot.moviestore.dao;

import java.util.List;

import com.grupodot.moviestore.entities.MoviePicture;

public interface MoviePictureDao {
	
	public void saveMoviePicture(MoviePicture moviePicture);
	
	public List<MoviePicture> queryAllMoviePicture(Integer movieId);

}
