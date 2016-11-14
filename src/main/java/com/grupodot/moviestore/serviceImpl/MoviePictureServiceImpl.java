package com.grupodot.moviestore.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.grupodot.moviestore.dao.MoviePictureDao;
import com.grupodot.moviestore.entities.MoviePicture;
import com.grupodot.moviestore.service.MoviePictureService;

@Service
public class MoviePictureServiceImpl implements MoviePictureService {
	
	@Autowired
	private MoviePictureDao moviePictureDao;
	
	@Transactional
	public void saveMoviePicture(MoviePicture moviePicture){
		moviePictureDao.saveMoviePicture(moviePicture);
	}

	@Transactional
	public List<MoviePicture> queryAllMoviePicture(Integer movieId) {
		return moviePictureDao.queryAllMoviePicture(movieId);
	}

	@Transactional
	public MoviePicture querybyID(Integer pictuareId) {
		return moviePictureDao.querybyID(pictuareId);
	}
	

}
