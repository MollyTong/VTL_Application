package com.example.virtualtrafficlight.Model;

import java.util.Objects;

public class Point {
    private double latitude;
    private double longtitude;
    private double speed;
    private double direction;
    private String token;
    private String role;
    public static Point point1 = new Point(-37.421998333333337d, -122.08400000000001d, 0);
    public static Point point2 = new Point(-37.421998333334d, 122.0840000005d, 0);

    public Point(double latitude, double longitude, double direction) {
        this.latitude = latitude;
        this.longtitude = longitude;
        this.direction = direction;
    }

    public double getLatitude(){
        return latitude;
    }

    public double getLongitude(){
        return longtitude;
    }

    public double getBearing(){
        return direction;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String token) {
        this.role = role;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(token, point.token);
    }

    @Override
    public int hashCode() {
        return Objects.hash(token);
    }

}
