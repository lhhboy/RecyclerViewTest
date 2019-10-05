package com.lhh.recyclerviewtest;

import android.graphics.Bitmap;

public class Fruit {
    private String name;
    private Bitmap imageId;

    public Fruit(String name, Bitmap imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public Bitmap getImageId() {
        return imageId;
    }
}
