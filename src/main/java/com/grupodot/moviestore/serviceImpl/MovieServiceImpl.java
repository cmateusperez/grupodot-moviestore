package com.grupodot.moviestore.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupodot.moviestore.dao.MovieDao;
import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.entities.MoviePicture;
import com.grupodot.moviestore.service.MoviePictureService;
import com.grupodot.moviestore.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	@Autowired
	private MovieDao movieDao;
	
	@Autowired
	private MoviePictureService moviePictureService;
	
	@Transactional
	public void saveMovie(Movie movie){
		movieDao.saveMovie(movie);
	}

	@Transactional
	public List<Movie> queryAllMovies() {
		return movieDao.queryAllMovies();
	}
	
	@Transactional
	public void queryMovieWithPicture(Movie movie){
		List<MoviePicture> moviePictures = moviePictureService.queryAllMoviePicture(movie.getId());
		movie.setMoviePictures(moviePictures);
	}
	
	@Transactional
	public Movie queryMovieById(Integer id){
		return null;
	
	}
	

}
