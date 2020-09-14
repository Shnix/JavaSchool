package util;

import entity.City;


public class DistanceCalculator {

    public static double calculateDistance(City start, City destination) {
        double lat1 = start.getLatitude();
        double lng1 = start.getLongitude();
        double lat2 = destination.getLatitude();
        double lng2 = destination.getLongitude();

        int earthRadius = 6371;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lng2 - lng1);
        double haversineFormula = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                        * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double additionalEquation = 2 * Math.atan2(Math.sqrt(haversineFormula), Math.sqrt(1 - haversineFormula));
        double distance = earthRadius * additionalEquation;
        return distance;
    }
}
