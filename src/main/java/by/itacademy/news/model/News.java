package by.itacademy.news.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

public class News {

    private String id;
    private String title;
    private String briefNews;
    private String content;
    private Date newsDate;

    private String imagePath;


    public News(String id, String title, String briefNews, String content, String imagePath) {
        this.id = id;
        this.title = title;
        this.briefNews = briefNews;
        this.content = content;
        this.imagePath = imagePath;
        this.newsDate = new Date();
//        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
    }


    public String getId() {
        return id;
    }


    public void setId(String id) {
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


    public Date getNewsDate() {
        return newsDate;
    }


    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) && Objects.equals(title, news.title) && Objects.equals(briefNews, news.briefNews) && Objects.equals(content, news.content) && Objects.equals(newsDate, news.newsDate) && Objects.equals(imagePath, news.imagePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, briefNews, content, newsDate, imagePath);
    }
}
