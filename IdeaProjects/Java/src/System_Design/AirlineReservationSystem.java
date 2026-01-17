package System_Design;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class AirlineReservationSystem {

    static class Flight {
        String flightNumber;
        String source;
        String destination;
        LocalDateTime departureTime;
        int capacity;
        Set<String> bookedPassengerIds = ConcurrentHashMap.newKeySet(); // Thread-safe

        public Flight(String flightNumber, String source, String destination, LocalDateTime departureTime, int capacity) {
            this.flightNumber = flightNumber;
            this.source = source;
            this.destination = destination;
            this.departureTime = departureTime;
            this.capacity = capacity;
        }

        public synchronized boolean bookSeat(String passengerId) {
            if (bookedPassengerIds.size() < capacity && !bookedPassengerIds.contains(passengerId)) {
                bookedPassengerIds.add(passengerId);
                return true;
            }
            return false;
        }

        public synchronized boolean cancelSeat(String passengerId) {
            return bookedPassengerIds.remove(passengerId);
        }

        public int getAvailableSeats() {
            return capacity - bookedPassengerIds.size();
        }

        @Override
        public String toString() {
            return "Flight[" + flightNumber + " | " + source + " -> " + destination +
                    " | " + departureTime + " | Seats Left: " + getAvailableSeats() + "]";
        }
    }

    static class Passenger {
        String id;
        String name;

        public Passenger(String id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    private final Map<String, Flight> flights = new HashMap<>();
    private final Map<String, Passenger> passengers = new HashMap<>();
    private final Map<String, List<String>> passengerBookings = new HashMap<>();

    public void addFlight(String flightNumber, String src, String dest, LocalDateTime time, int capacity) {
        flights.put(flightNumber, new Flight(flightNumber, src, dest, time, capacity));
    }

    public void registerPassenger(String id, String name) {
        passengers.put(id, new Passenger(id, name));
        passengerBookings.put(id, new ArrayList<>());
    }

    public boolean bookTicket(String passengerId, String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null && passengers.containsKey(passengerId)) {
            boolean success = flight.bookSeat(passengerId);
            if (success) {
                passengerBookings.get(passengerId).add(flightNumber);
                return true;
            }
        }
        return false;
    }

    public boolean cancelTicket(String passengerId, String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null && passengers.containsKey(passengerId)) {
            boolean success = flight.cancelSeat(passengerId);
            if (success) {
                passengerBookings.get(passengerId).remove(flightNumber);
                return true;
            }
        }
        return false;
    }

    public void showFlightInfo(String flightNumber) {
        Flight flight = flights.get(flightNumber);
        if (flight != null) {
            System.out.println(flight);
        } else {
            System.out.println("Flight not found.");
        }
    }

    public void showPassengerBookings(String passengerId) {
        List<String> bookings = passengerBookings.get(passengerId);
        if (bookings != null && !bookings.isEmpty()) {
            System.out.println("Bookings for " + passengerId + ": " + bookings);
        } else {
            System.out.println("No bookings for this passenger.");
        }
    }

    // Demo
    public static void main(String[] args) {
        AirlineReservationSystem system = new AirlineReservationSystem();

        system.addFlight("AI101", "Delhi", "Mumbai", LocalDateTime.of(2025, 8, 1, 10, 30), 2);
        system.addFlight("AI202", "Delhi", "Bangalore", LocalDateTime.of(2025, 8, 1, 15, 45), 1);

        system.registerPassenger("P001", "Sagor");
        system.registerPassenger("P002", "Amit");

        system.bookTicket("P001", "AI101");
        system.bookTicket("P002", "AI101");

        system.bookTicket("P001", "AI202"); // another booking
        system.cancelTicket("P001", "AI101");

        system.showFlightInfo("AI101");
        system.showPassengerBookings("P001");
    }
}

