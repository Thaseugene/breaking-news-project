package by.itacademy.news.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class News {

    private String id;
    private String title;
    private String briefNews;
    private String content;
    private String newsDate;

    private String imagePath;


    public News(String id, String title, String briefNews, String content, String imagePath) {
        this.id = id;
        this.title = title;
        this.briefNews = briefNews;
        this.content = content;
        this.imagePath = imagePath;
        this.newsDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
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


    public String getNewsDate() {
        return newsDate;
    }


    public void setNewsDate(String newsDate) {
        this.newsDate = newsDate;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
