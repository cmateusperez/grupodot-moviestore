package com.grupodot.moviestore.service;

import java.util.List;

import com.grupodot.moviestore.entities.MoviePicture;


public interface MoviePictureService {
	
	public void saveMoviePicture(MoviePicture moviePicture);
	
	public List<MoviePicture> queryAllMoviePicture(Integer movieId);
	
	public MoviePicture querybyID(Integer pictuareId);


}
