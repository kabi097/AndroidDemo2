package com.example.recyclerview2;

import java.util.Date;

public class Event {
    public String name;
    public int image_resource;
    public Date date;
    public String description;

    public Event(String name, int image_resource, Date date, String description) {
        this.name = name;
        this.image_resource = image_resource;
        this.date = date;
        this.description = description;
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
}
