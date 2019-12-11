package com.opencredo.examples.akkajava;

import java.util.List;

public class Location {
    public List<Integer> location;

    public Location(List<Integer> loc){
        this.location = loc;
    }
    @Override
    public String toString() {
        return location.toString();
    }
}
