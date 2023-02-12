package by.itacademy.news.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class News implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String title;
    private String briefNews;
    private String content;
    private Date creationDate;
    private Date publicationDate;
    private int authorId;
    private boolean isActive;
    private String imagePath;


    public News(int id, String title, String briefNews, String content, String imagePath, Date creationDate,Date publicationDate, boolean isActive, int authorId) {
        this.id = id;
        this.title = title;
        this.briefNews = briefNews;
        this.content = content;
        this.imagePath = imagePath;
        this.creationDate = creationDate;
        this.publicationDate = publicationDate;
        this.isActive = isActive;
        this.authorId = authorId;
    }


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getBriefNews() {
        return briefNews;
    }


    public void setBriefNews(String briefNews) {
        this.briefNews = briefNews;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public Date getCreationDate() {
        return creationDate;
    }


    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return authorId == news.authorId && isActive == news.isActive && Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(briefNews, news.briefNews) && Objects.equals(content, news.content) && Objects.equals(creationDate, news.creationDate) && Objects.equals(publicationDate, news.publicationDate) && Objects.equals(imagePath, news.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, briefNews, content, creationDate, publicationDate, authorId, isActive, imagePath);
    }
}
