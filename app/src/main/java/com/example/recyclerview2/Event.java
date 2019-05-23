package com.example.recyclerview2;

import android.net.Uri;

import java.util.Date;

public class Event {
    public String name;
    public int image_resource;
    public Date date;
    public String description;
    public String photo_uri;
    private boolean has_photo;


    public Event(String name, Date date, String description) {
        this.name = name;
        this.image_resource = image_resource;
        this.date = date;
        this.description = description;
        has_photo = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage_resource() {
        return image_resource;
    }

    public void setImage_resource(int image_resource) {
        this.image_resource = image_resource;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getPhoto_uri() {
        return photo_uri;
    }

    public void setPhoto_uri(String photo_uri) {
        this.photo_uri = photo_uri;
    }

    public boolean isHas_photo() {
        return has_photo;
    }

    public void setHas_photo(boolean has_photo) {
        this.has_photo = has_photo;
    }

}