package com.grupodot.moviestore.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grupodot.moviestore.dao.MovieDao;
import com.grupodot.moviestore.entities.Movie;
import com.grupodot.moviestore.entities.MoviePicture;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveMovie(Movie movie) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(movie);
	}

	@SuppressWarnings("unchecked")
	public List<Movie> queryAllMovies() {
		return this.sessionFactory.getCurrentSession().createCriteria(Movie.class).list();
	}
	
	public Movie queryMovieById(Integer id){
		return (Movie) this.sessionFactory.getCurrentSession()
				.createCriteria(Movie.class).add(Restrictions.eq("id", id)).uniqueResult();		
	}
	
	public List<Movie> queryMovieByName(String name){
		List<Movie> results = new ArrayList<>();
		for (Object movie : sessionFactory.getCurrentSession()
				.createCriteria(Movie.class).add(Restrictions.ilike("name", name)).list()) {
			//Hibernate.initialize(((Movie) movie).getMoviePictures());
			((Movie) movie).setMoviePictures(new ArrayList<MoviePicture>());
			results.add((Movie) movie);
		}
		return results;		
	}
}
