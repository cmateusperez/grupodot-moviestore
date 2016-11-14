package com.grupodot.moviestore.daoImpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.grupodot.moviestore.dao.MoviePictureDao;
import com.grupodot.moviestore.entities.MoviePicture;

@Repository
public class MoviePictureDaoImpl implements MoviePictureDao {

	@Autowired
	private SessionFactory sessionFactory;

	public void saveMoviePicture(MoviePicture moviePicture) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	public List<MoviePicture> queryAllMoviePicture(Integer movieId) {
		return this.sessionFactory.getCurrentSession()
				.createCriteria(MoviePicture.class)
				.add(Restrictions.eq("movie.id", movieId)).list();
	}
	
	public MoviePicture querybyID(Integer pictuareId) {
		return (MoviePicture) this.sessionFactory.getCurrentSession()
				.createCriteria(MoviePicture.class)
				.add(Restrictions.eq("pictureId", pictuareId)).uniqueResult();
	}


}
