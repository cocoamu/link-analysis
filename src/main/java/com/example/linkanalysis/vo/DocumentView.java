package com.example.linkanalysis.vo;

public class DocumentView {
    private String title;
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DocumentView{"
                + "title='" + title + '\''
                + ", imageUrl='" + imageUrl + '\''
                + '}';
    }
}