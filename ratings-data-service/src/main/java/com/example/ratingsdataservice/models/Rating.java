package com.example.ratingsdataservice.models;

public class Rating {
    private String itemId;
    private int rating;

    public Rating(String itemId, int rating) {
        this.itemId = itemId;
        this.rating = rating;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
