package com.example.virtualtrafficlight.Repository;
import com.example.virtualtrafficlight.Model.Point;



public class ConflictDetection {
    private static final  double EARTH_RADIUS = 6378137;
    private static final double toDistance = 500;

    public static boolean checkRange(Point point, Point intersection){
        double distance = getDistance(point, intersection);
        return distance <= toDistance;
    }

    public static double getDistance(Point point, Point intersection){

        double lon1 = point.getLongitude();
        double lat1 = point.getLatitude();
        double lon2 = intersection.getLongitude();
        double lat2 = intersection.getLatitude();
        double radLat1 = Math.toRadians(lat1);
        double radLat2 = Math.toRadians(lat2);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lon1-lon2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = (double)Math.round(s * 10000) / 10000;
        s = s * 10000/ 10000;
        return s * 1000d;
}

    public static double getbearing(Point point, Point intersection){
        double lat1 = point.getLatitude();
        double lon1 = point.getLongitude();
        double lat2 = intersection.getLatitude();
        double lon2 = intersection.getLongitude();
        double longitude1 = lon1;
        double longitude2 = lon2;
        double latitude1 = Math.toRadians(lat1);
        double latitude2 = Math.toRadians(lat2);
        double longDiff = Math.toRadians(longitude2-longitude1);
        double y = Math.sin(longDiff)*Math.cos(latitude2);
        double x = Math.cos(latitude1)*Math.sin(latitude2)-Math.sin(latitude1)*Math.cos(latitude2)*Math.cos(longDiff);
        return (Math.toDegrees(Math.atan2(y, x))+360)%360;
    }


}