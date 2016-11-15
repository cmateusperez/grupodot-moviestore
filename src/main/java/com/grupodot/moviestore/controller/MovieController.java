package com.grupodot.moviestore.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.entities.MoviePicture;
import com.grupodot.moviestore.service.MoviePictureService;
import com.grupodot.moviestore.service.MovieService;

@Controller
@Scope("session")
public class MovieController{

	@Autowired
	private MovieService movieService;

	@Autowired
	private MoviePictureService moviePictureService;

	private Movie movie = new Movie();

	private MoviePicture moviePicture;

	private List<Movie> movieList;

	private UploadedFile file;

	private Movie selectedMovie;


	@PostConstruct
	public void initMovieController() {
		setMovieList(movieService.queryAllMovies());
		for (Movie movie : movieList) {
			movieService.queryMovieWithPicture(movie);
		}
      selectedMovie = movieList.get(0);
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

	/**
	 * Save movie
	 */
	public void saveMovie() {
		FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Creación exitosa"));
		movieService.saveMovie(movie);
		movie = new Movie();
	}


	/**
	 * Save movie image
	 * @param event
	 */
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

	/**
	 * Retrieve image by id from database
	 * @return
	 */
	public StreamedContent getImageFromDB() {
		FacesContext context = FacesContext.getCurrentInstance();

		if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
			return new DefaultStreamedContent();
		} else {

			String pictureId = context.getExternalContext().getRequestParameterMap().get("pictureId");
			MoviePicture picture = moviePictureService.querybyID(new Integer(pictureId));

			return new DefaultStreamedContent(new ByteArrayInputStream(picture.getData()), picture.getMimeType());
		}
	}
	
	public void onRowSelect(SelectEvent event) {
		this.selectedMovie = (Movie) event.getObject();
	}

	public void showMovie() {
		moviePictureService.queryAllMoviePicture(selectedMovie.getId());

	}



}
