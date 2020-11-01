package com.example.virtualtrafficlight.Repository;

import com.example.virtualtrafficlight.Model.Point;

import java.util.Map;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;


public class Repository {
    private static Point intersection = new Point(37.421998333333334d, -122.08400000000001d, 0);
    private static Map<String, ArrayList<Point>> carMap = new ConcurrentHashMap<>();
    private static Point leader = null;
    private static String token = "000111ABC";


    public boolean onMessage(double Longitude, double Latitude, double direction) {
        // store the information
        Point point = new Point(Longitude, Latitude, direction);
        point.setToken(token);
        // check the distance
        if (ConflictDetection.checkRange(point, intersection)) {
            ArrayList<Point> pointList = carMap.get(point.getToken());
            if (pointList == null) {
                pointList = new ArrayList<>();
//                pointList.add(Point.point1);
//                pointList.add(Point.point2);
                carMap.put(point.getToken(), pointList);
            }
            // compute the direction
            pointList.add(point);
            if (pointList.size() > 1) {
                // when the car is getting away from the intersection, remove the car
                if (ConflictDetection.getDistance(point, intersection) >=
                        ConflictDetection.getDistance(pointList.get(pointList.size() - 2), intersection)) {
                    carMap.remove(point.getToken());
                    if (point.equals(leader)) {
                        leader = null;
                        return true;
                    }
                } else {
                    // select as the leader
                    if (leader == null) {
                        leader = point;
                        // pass green light
                        return false;
                    } else {
                        // check the direction is the same or not
                        double leadingDirection = ConflictDetection.getbearing(leader, intersection);
                        double pointDirection = ConflictDetection.getbearing(point, intersection);
                        double result = Math.abs(leadingDirection - pointDirection);
                        // same direction, send green light
                        if (result < 20 || (result > 170 && result < 190)) {
                            return true;
                        } else {
                            // vertical direction, send red light
                            return false;
                        }
                    }
                }
            }
            return true;
        } else {
            // clear the element
            if(carMap.get(point.getToken()) != null){
                carMap.remove(point.getToken());
            }
            return true;
        }

    }
}



