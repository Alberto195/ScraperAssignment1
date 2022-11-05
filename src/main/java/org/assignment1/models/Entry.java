package org.assignment1.models;

public class Entry {
    String heading;
    String description;
    String url;
    String imageUrl;
    String priceInCents;

    public Entry(String heading, String description, String url, String imageUrl, String priceInCents) {
        this.heading = heading;
        this.description = description;
        this.url = url;
        this.imageUrl = imageUrl;
        this.priceInCents = priceInCents;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getHeading() {
        return heading;
    }

    public String getPriceInCents() {
        return priceInCents;
    }

    public String getUrl() {
        return url;
    }
}
