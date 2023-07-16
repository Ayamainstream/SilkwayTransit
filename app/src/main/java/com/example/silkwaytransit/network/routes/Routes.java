package com.example.silkwaytransit.network.routes;

import java.util.ArrayList;

public class Routes {
    private ArrayList<String> Stations;
    private Boolean isActive;
    private String id;

    public ArrayList<String> getStations() {
        return Stations;
    }

    public void setStations(ArrayList<String> stations) {
        Stations = stations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
