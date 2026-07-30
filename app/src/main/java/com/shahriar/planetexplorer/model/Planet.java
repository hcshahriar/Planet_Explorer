package com.shahriar.planetexplorer.model;

import java.io.Serializable;


public class Planet implements Serializable {

    private String name;
    private String type;
    private String distance;
    private String gravity;
    private String moons;
    private String temperature;
    private String missionStatus;
    private String fact;
    private int image;
    private boolean isFavorited = false; // <-- ADDED: Stores heart state

    public Planet(String name,
                  String type,
                  String distance,
                  String gravity,
                  String moons,
                  String temperature,
                  String missionStatus,
                  String fact,
                  int image) {

        this.name = name;
        this.type = type;
        this.distance = distance;
        this.gravity = gravity;
        this.moons = moons;
        this.temperature = temperature;
        this.missionStatus = missionStatus;
        this.fact = fact;
        this.image = image;
    }

    public Planet(String name,
                  String type,
                  String distance,
                  String fact,
                  int image) {
        this(name, type, distance, "N/A", "N/A", "N/A", "N/A", fact, image);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDistance() {
        return distance;
    }

    public String getGravity() {
        return gravity;
    }

    public String getMoons() {
        return moons;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getMissionStatus() {
        return missionStatus;
    }

    public String getFact() {
        return fact;
    }

    public int getImage() {
        return image;
    }

    // <-- ADDED: Checks if heart is favorited
    public boolean isFavorited() {
        return isFavorited;
    }

    // <-- ADDED: Updates favorited state when tapped
    public void setFavorited(boolean favorited) {
        isFavorited = favorited;
    }
}