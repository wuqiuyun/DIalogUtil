package com.example.joey.bean;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * @author joey
 *         created at 2017/8/14 21:42 
 */
public class Fruit {
    private int photo;
    private String name;

    public Fruit(int photo, String name) {
        this.photo = photo;
        this.name = name;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit{" +
                "photo=" + photo +
                ", name='" + name + '\'' +
                '}';
    }
}
