package com.example.lostbox;

public class ArticleModel {
    private String userId;
    private String title;
    private long createdAt;
    private String place;
    private String pay;
    private String price;
    private String imageURL;

    public ArticleModel() {
        // 기본 생성자 (Firebase에서 사용하기 위해 필요)
    }

    public ArticleModel(String userId, String title, long createdAt, String place, String pay, String imageURL) {
        this.userId = userId;
        this.title = title;
        this.createdAt = createdAt;
        this.place = place;
        this.pay = pay;
        this.imageURL = imageURL;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
