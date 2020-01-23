package ru.sibers.core;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import ru.sibers.database.DataBase;

/**
 * This class represents one system. Each system has Map and database. 
 * @author Grigoriy Yakovlev
 * @version 1.0
 */

public class Portal {
	
	private static Portal system;///singleton
	
	//private Map<Integer, News> news;
	private DataBase db;
	private Portal() {///singleton
		//news = new HashMap<Integer, News>();
		db = new DataBase();
		
	}
	
	public static Portal getPortal() {///singleton
		if(system==null) {
			system = new Portal();
			
		}
		return system;
	}
	
	

	public void	addNews(String title, String body, String path, String description) throws ClassNotFoundException, SQLException {
		if(title==null) {
			throw new NullPointerException("Title is not set");
		}
		
		if(title.trim().length()==0) {
			throw new IllegalArgumentException("Title is empty");
		}
		
		if(body==null) {
			throw new NullPointerException("Body is not set");
		}
		
		if(body.trim().length()==0) {
			throw new IllegalArgumentException("Body is empty");
		}
		Date pubDate = new Date();
		News news = new News(0, title, pubDate, body, path, description);
		addNews(news);
		
	}
	
	public void	addNews(News news) throws ClassNotFoundException, SQLException {
		
		
		db.addToDataBase(news);
	}
	
	public void addNews(String title, String body) throws ClassNotFoundException, SQLException {
		if(title==null) {
			throw new NullPointerException("Title is not set");
		}
		
		if(title.trim().length()==0) {
			throw new IllegalArgumentException("Title is empty");
		}
		
		if(body==null) {
			throw new NullPointerException("Body is not set");
		}
		
		if(body.trim().length()==0) {
			throw new IllegalArgumentException("Body is empty");
		}
		
		
		News news = new News(title, body);
		addNews(news);
	}

	public List<News> getNews(int start, Count count) throws SQLException, ClassNotFoundException {
		if(start<0) {
			throw new IllegalArgumentException("Uncorrect start");
		}
		
		
		try {
			db.countOfNotes();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<News> news =  db.getFromDataBase(start, count.getCount());
		
		
        Collections.sort(news);
        return news;
	}

	public int getCount() throws ClassNotFoundException, SQLException {
		
		return db.countOfNotes();
	}
	
	
}
