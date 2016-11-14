package com.grupodot.moviestore.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grupodot.moviestore.dao.MovieDao;
import com.grupodot.moviestore.entities.Movie;

@Repository
public class MovieDaoImpl implements MovieDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveMovie(Movie movie) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(movie);
	}

	@SuppressWarnings("unchecked")
	public List<Movie> queryAllMovies() {
		return this.sessionFactory.getCurrentSession().createCriteria(Movie.class).list();
	}

}
