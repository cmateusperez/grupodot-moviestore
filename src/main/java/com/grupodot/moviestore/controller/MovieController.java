package com.grupodot.moviestore.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.entities.MoviePicture;
import com.grupodot.moviestore.service.MoviePictureService;
import com.grupodot.moviestore.service.MovieService;

@Controller
public class MovieController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private MovieService movieService;

	@Autowired
	private MoviePictureService moviePictureService;

	private Movie movie = new Movie();

	private MoviePicture moviePicture;

	private List<Movie> movieList;

	private UploadedFile file;

	private Movie selectedMovie;
	
	private List<String> images = new ArrayList<String>();

	@PostConstruct
	public void initMovieController() {
		setMovieList(movieService.queryAllMovies());
		for(Movie movie: movieList){
			movieService.queryMovieWithPicture(movie);
		}
		
		images.add("Chrysanthemun.jpg");
		images.add("Desert.jpg");
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public List<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(List<Movie> movieList) {
		this.movieList = movieList;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public MoviePicture getMoviePicture() {
		return moviePicture;
	}

	public void setMoviePicture(MoviePicture moviePicture) {
		this.moviePicture = moviePicture;
	}

	public Movie getSelectedMovie() {
		return selectedMovie;
	}

	public void setSelectedMovie(Movie selectedMovie) {
		this.selectedMovie = selectedMovie;
	}

	public void saveMovie() {
		movieService.saveMovie(movie);
	}
	

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
	
	

	public void saveMoviePicture(FileUploadEvent event) {
		moviePicture = new MoviePicture();
		moviePicture.setData(event.getFile().getContents());
		moviePicture.setMimeType(event.getFile().getContentType());
		moviePicture.setName(event.getFile().getFileName());
		movieService.queryMovieWithPicture(selectedMovie);
		selectedMovie.getMoviePictures().add(moviePicture);
		moviePicture.setMovie(selectedMovie);
		movieService.saveMovie(selectedMovie);
	}

	public void onRowSelect(SelectEvent event) {
		this.selectedMovie = (Movie) event.getObject();
	}
	
	

	public void showMovie() {
		moviePictureService.queryAllMoviePicture(selectedMovie.getId());

	}
	



}
