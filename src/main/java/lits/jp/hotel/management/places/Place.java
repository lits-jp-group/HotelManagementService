package lits.jp.hotel.management.places;

import java.util.ArrayList;
import java.util.List;

public class Place {
    private List<String> placeTypes = new ArrayList<>();
    private double lat = -1, lng = -1;
    private String name;
    private String addr;

    public Place() {
    }

    public Place(List<String> placeTypes, double lat, double lng, String name, String addr) {
        this.name = name;
        this.placeTypes = placeTypes;
        this.addr = addr;
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "\nPlace name: " + name + "; address: " + addr + ". Coordinates: lat=" + lat + ", lng=" + lng + "\nType: " + placeTypes;
    }

    public List<String> getPlaceTypes() {
        return placeTypes;
    }

    public void setPlaceTypes(List<String> placeTypes) {
        this.placeTypes = placeTypes;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
