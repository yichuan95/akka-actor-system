package com.opencredo.examples.akkajava;

public class Picture {
    public String picName;

    public Picture(String name){
        this.picName = name;
    }
    @Override
    public String toString() {
        return picName;
    }
}