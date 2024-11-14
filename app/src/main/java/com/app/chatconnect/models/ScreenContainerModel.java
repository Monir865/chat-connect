package com.app.chatconnect.models;

public class ScreenContainerModel {
    private int image;
    private String title;
    private String paragraph;

    public ScreenContainerModel(int image, String title, String paragraph) {
        this.image = image;
        this.title = title;
        this.paragraph = paragraph;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getParagraph() {
        return paragraph;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }
}
