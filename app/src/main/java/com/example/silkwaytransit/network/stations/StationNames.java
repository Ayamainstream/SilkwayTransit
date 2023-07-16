package com.example.silkwaytransit.network.stations;

public class StationNames {
    private String Name;
    private String id;
    private String Geolocation;

    public StationNames() {
    }

    public StationNames(String name, String id) {
        Name = name;
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGeolocation() {
        return Geolocation;
    }

    public void setGeolocation(String geolocation) {
        Geolocation = geolocation;
    }
}
