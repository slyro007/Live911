package com.github.slyro007.nuhack91;

/**
 * Created by MLH Admin on 3/25/2018.
 */

public class Location {

    String locationId;
    String locationRoom;
    String locationBuilding;

    public Location(){

    }

    public Location(String locationId, String locationRoom, String locationBuilding) {
        this.locationId = locationId;
        this.locationRoom = locationRoom;
        this.locationBuilding = locationBuilding;
    }

    public String getLocationId() {
        return locationId;
    }

    public String getLocationRoom() {
        return locationRoom;
    }

    public String getLocationBuilding() {
        return locationBuilding;
    }
}
