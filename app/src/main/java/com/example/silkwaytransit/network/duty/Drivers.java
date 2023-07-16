package com.example.silkwaytransit.network.duty;

public class Drivers {
    private String FullName;
    private Boolean Status;
    private String id;
    private String Routes;

    public Drivers(){}

    public Drivers(String fullName, Boolean status, String id){
        this.FullName = fullName;
        this.Status = status;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        this.FullName = fullName;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }

    public String getRoutes() {
        return Routes;
    }

    public void setRoutes(String routes) {
        Routes = routes;
    }

    @Override
    public String toString() {
        return "Drivers{" +
                "FullName='" + FullName + '\'' +
                ", Status=" + Status +
                ", id='" + id + '\'' +
//                ", Routes=" + Routes +
                '}';
    }
}
