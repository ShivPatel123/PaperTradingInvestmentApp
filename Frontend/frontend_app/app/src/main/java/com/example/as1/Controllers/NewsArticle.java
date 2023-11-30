package com.example.as1.Controllers;

public class NewsArticle {
    String title, url, summary, source, image;
    public String[] authors = {};

    public NewsArticle(String title, String url, String summary, String source, String image, String[] authors) {
        this.title = title;
        this.url = url;
        this.summary = summary;
        this.source = source;
        this.image = image;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String[] getAuthors() {
        return authors;
    }

    public void setAuthors(String[] authors) {
        this.authors = authors;
    }

}
