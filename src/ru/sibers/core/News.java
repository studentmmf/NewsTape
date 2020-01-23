package ru.sibers.core;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * This class represents one news. Each news has  title  body, date. Some news has image.
 * @author Grigoryi Yakovlev
 * @version 1.0
 */
public class News implements Comparable<News>{

    private int id;
	private String title;
	private Date pubDate;
	private String body;
	private Image image;	
	
	
	
	private class Image {
		private String path;
		private String description;
		public Image(String path, String description) {
			
			this.path = path;
			this.description = description;
		}	
	}
	
	
    
	public News(int id, String title, Date pubDate, String body, String path, String description) {
		
		if(id<0) {
			throw new IllegalArgumentException("Uncorrect id");
		}
		
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
		Image image = new Image(path, description);
		this.id = id;
		this.title = title;
		this.pubDate = pubDate;
		this.body = body;
		this.image = image;
	}
	
	public News(int id, String title, Date pubDate, String body) {
		
		this(id, title, pubDate, body, "", "");
		
	}
	
	public News(String title, String body) {
		
		this(0 ,title, null, body, "", "");
		
	}

	public String toString() {
		return "bla";
	}
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		if(id<0) {
			throw new IllegalArgumentException("Uncorrect id");
		}
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if(title==null) {
			throw new NullPointerException("Title is not set");
		}
		
		if(title.trim().length()==0) {
			throw new IllegalArgumentException("Title is empty");
		}
		this.title = title;
	}

	public Date getPubDate() {
		return pubDate;
	}

	public void setPubDate(Date pubDate) {
		this.pubDate = pubDate;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		if(body==null) {
			throw new NullPointerException("Body is not set");
		}
		
		if(body.trim().length()==0) {
			throw new IllegalArgumentException("Body is empty");
		}
		this.body = body;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	
	public String html() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		String dateString = format.format(pubDate);
		String html = "<h2>"+ id + " " +title+"</h2>" + "<h6>"+dateString+"</h6>" +
                "<p>"+body+"</p>";
                if(!image.path.isEmpty()) {
                	html += "<img src=\"" + image.path + "\" alt=\"" + image.description + "\">";
                }
        return html;
		
	}
	
	public String getImagePath() {
		return image.path;
	}
	public String getImageDescription() {
		return image.description;
	}
	
	public boolean hasImage() {
		return !getImagePath().equals("");
	}

	@Override
	public int compareTo(News o) {
		if(this.pubDate.getTime() < o.pubDate.getTime()) {
			return 1;
		}
		else {
			if(this.pubDate.getTime() > o.pubDate.getTime()) {
				return -1;
			}
		}
		return 0;
	}

	

}
