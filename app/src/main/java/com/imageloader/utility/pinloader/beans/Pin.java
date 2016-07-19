package com.imageloader.utility.pinloader.beans;


public class Pin {
    String id;
    int likes;
    String userName;
    String categories;
    String createdAt;
    String imageSmall;
    String imageLarge;

    public Pin(String id, int likes, String userName, String categories, String createdAt, String imageSmall, String imageLarge) {
        this.id = id;
        this.likes = likes;
        this.userName = userName;
        this.categories = categories;
        this.createdAt = createdAt;
        this.imageSmall = imageSmall;
        this.imageLarge = imageLarge;
    }

    public Pin() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageSmall() {
        return imageSmall;
    }

    public void setImageSmall(String imageSmall) {
        this.imageSmall = imageSmall;
    }

    public String getImageLarge() {
        return imageLarge;
    }

    public void setImageLarge(String imageLarge) {
        this.imageLarge = imageLarge;
    }
}
