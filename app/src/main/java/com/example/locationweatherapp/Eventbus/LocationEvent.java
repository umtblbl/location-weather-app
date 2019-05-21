package com.example.locationweatherapp.Eventbus;

import android.location.Location;

public class LocationEvent {

    public Location location;

    public LocationEvent(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
