package com.example.silkwaytransit.network.stations;

public class StationTime {
    private String ArrivalTime;
    private String DepartureTime;
    private String StationNames;
    private String DriverId;
    private String id;

    public String getStationNames() {
        return StationNames;
    }

    public void setStationNames(String stationNames) {
        StationNames = stationNames;
    }

    public String getDriverId() {
        return DriverId;
    }

    public void setDriverId(String driverId) {
        DriverId = driverId;
    }

    public String getArrivalTime() {
        return ArrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        ArrivalTime = arrivalTime;
    }

    public String getDepartureTime() {
        return DepartureTime;
    }

    public void setDepartureTime(String departureTime) {
        DepartureTime = departureTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
