package System_Design;

import java.util.*;
import java.util.stream.Collectors;

public class RideSharingSystem {

    enum Status { AVAILABLE, BUSY }

    static class Location {
        double latitude;
        double longitude;

        public Location(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public double distanceTo(Location other) {
            // Haversine formula or simplified Euclidean distance
            double latDiff = this.latitude - other.latitude;
            double longDiff = this.longitude - other.longitude;
            return Math.sqrt(latDiff * latDiff + longDiff * longDiff);
        }
    }

    static class Driver {
        String id;
        String name;
        Location location;
        Status status;

        public Driver(String id, String name, Location location) {
            this.id = id;
            this.name = name;
            this.location = location;
            this.status = Status.AVAILABLE;
        }
    }

    static class Rider {
        String id;
        String name;
        Location location;

        public Rider(String id, String name, Location location) {
            this.id = id;
            this.name = name;
            this.location = location;
        }
    }

    // In-memory storage
    private Map<String, Driver> driverMap = new HashMap<>();
    private Map<String, Rider> riderMap = new HashMap<>();

    // Register driver
    public void registerDriver(String id, String name, double lat, double lon) {
        driverMap.put(id, new Driver(id, name, new Location(lat, lon)));
    }

    // Register rider
    public void registerRider(String id, String name, double lat, double lon) {
        riderMap.put(id, new Rider(id, name, new Location(lat, lon)));
    }

    // Update driver location
    public void updateDriverLocation(String driverId, double lat, double lon) {
        Driver driver = driverMap.get(driverId);
        if (driver != null) {
            driver.location = new Location(lat, lon);
        }
    }

    // Update rider location
    public void updateRiderLocation(String riderId, double lat, double lon) {
        Rider rider = riderMap.get(riderId);
        if (rider != null) {
            rider.location = new Location(lat, lon);
        }
    }

    // Find nearest available driver
    public Driver matchDriver(String riderId) {
        Rider rider = riderMap.get(riderId);
        if (rider == null) return null;

        Driver nearestDriver = null;
        double minDistance = Double.MAX_VALUE;

        for (Driver driver : driverMap.values()) {
            if (driver.status == Status.AVAILABLE) {
                double dist = driver.location.distanceTo(rider.location);
                if (dist < minDistance) {
                    minDistance = dist;
                    nearestDriver = driver;
                }
            }
        }

        if (nearestDriver != null) {
            nearestDriver.status = Status.BUSY;
        }
        return nearestDriver;
    }

    // Mark ride complete
    public void completeRide(String driverId) {
        Driver driver = driverMap.get(driverId);
        if (driver != null) {
            driver.status = Status.AVAILABLE;
        }
    }

    // Show available drivers
    public List<Driver> getAvailableDrivers() {
        return driverMap.values().stream()
                .filter(d -> d.status == Status.AVAILABLE)
                .collect(Collectors.toList());
    }

    // Demo
    public static void main(String[] args) {
        RideSharingSystem app = new RideSharingSystem();

        app.registerDriver("d1", "Ravi", 12.9, 77.6);
        app.registerDriver("d2", "Amit", 13.0, 77.5);
        app.registerRider("r1", "Sagor", 12.95, 77.55);

        Driver matched = app.matchDriver("r1");
        if (matched != null) {
            System.out.println("Matched driver: " + matched.name);
        } else {
            System.out.println("No driver found");
        }

        // Complete ride
        app.completeRide(matched.id);
    }
}

