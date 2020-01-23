package ru.sibers.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import ru.sibers.core.News;

public class DataBase {
	private static Connection con;

	

	public int countOfNotes() throws ClassNotFoundException, SQLException {
		String sql = "SELECT COUNT(id) FROM news_tape; ";
		int count = 0;
		Connection c = getConnection();
		ResultSet rs;
		PreparedStatement ps = c.prepareStatement(sql);
		rs = ps.executeQuery();

		if (rs.next()) {
			count = rs.getInt(1);
			System.out.println("Total number of news : " + count);
		}

		return count;
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException {
		
		String url = "jdbc:mysql://localhost:3306/newsonline?zeroDateTimeBehavior=convertToNull&autoReconnect = true&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String password = "";
		Class.forName("com.mysql.cj.jdbc.Driver");

		con = DriverManager.getConnection(url, user, password);
		
		return con;
	}

	public void addToDataBase(News news) throws ClassNotFoundException, SQLException {
		Connection c = getConnection();
		String sql = "";

		if (!news.hasImage()) {

			sql = "INSERT INTO news_tape (title, body) VALUES ('" + news.getTitle() + "', '" + news.getBody() + "');";
		} else {
			sql = "INSERT INTO news_tape (title, body, imagePath, description) VALUES ('" + news.getTitle() + "', '"
					+ news.getBody() + "', '" + news.getImagePath() + "', '" + news.getImageDescription() + "');";
		}
		System.out.println(sql);
		PreparedStatement ps = c.prepareStatement(sql);
		ps.executeUpdate();

	}

	public List<News> getFromDataBase(int start, int quantity) throws SQLException, ClassNotFoundException {
		Connection c = getConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM news_tape LIMIT " + start + ", " + quantity + ";");
		List<News> news = new ArrayList<News>();
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			if (rs.getString("imagePath") == null) {
				news.add(
						new News(rs.getInt("id"), rs.getString("title"), rs.getTimestamp("pubDate"), rs.getString("body")));
			} else {
				news.add(new News(rs.getInt("id"), rs.getString("title"), rs.getTimestamp("pubDate"),
						rs.getString("body"), rs.getString("imagePath"), rs.getString("description")));
			}
		}
		return news;
	}

}
