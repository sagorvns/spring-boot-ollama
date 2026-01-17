package System_Design;

import java.util.*;

enum VehicleType { BIKE, CAR, TRUCK }
enum ParkingSlotType { COMPACT, LARGE }

class Vehicle {
    String number;
    VehicleType type;

    // Constructor to initialize a vehicle with its number and type
    public Vehicle(String number, VehicleType type) {
        this.number = number;
        this.type = type;
    }
}

class ParkingSlot {
    int slotNumber;
    ParkingSlotType type;
    boolean occupied;

    // Constructor to initialize a parking slot with its number and type
    public ParkingSlot(int slotNumber, ParkingSlotType type) {
        this.slotNumber = slotNumber;
        this.type = type;
        this.occupied = false; // Initially, the slot is not occupied
    }
}

class ParkingLot {
    private Map<Vehicle, ParkingSlot> parkedVehicles = new HashMap<>();
    private List<ParkingSlot> slots = new ArrayList<>();

    // Constructor to initialize the parking lot with a given number of slots
    public ParkingLot(int numSlots) {
        for (int i = 1; i <= numSlots; i++) {
            // Alternate between LARGE and COMPACT slots
            slots.add(new ParkingSlot(i, i % 2 == 0 ? ParkingSlotType.LARGE : ParkingSlotType.COMPACT));
        }
    }

    // Method to park a vehicle in an available slot
    public boolean parkVehicle(Vehicle vehicle) {
        for (ParkingSlot slot : slots) {
            // Check if the slot is not occupied and is suitable for the vehicle
            if (!slot.occupied && (slot.type == ParkingSlotType.LARGE || vehicle.type == VehicleType.BIKE)) {
                slot.occupied = true; // Mark the slot as occupied
                parkedVehicles.put(vehicle, slot); // Map the vehicle to the slot
                System.out.println("Parked at slot: " + slot.slotNumber);
                return true;
            }
        }
        System.out.println("No slot available");
        return false;
    }

    // Method to remove a vehicle from its parked slot
    public void removeVehicle(Vehicle vehicle) {
        if (parkedVehicles.containsKey(vehicle)) {
            ParkingSlot slot = parkedVehicles.remove(vehicle); // Remove the vehicle from the map
            slot.occupied = false; // Mark the slot as not occupied
            System.out.println("Vehicle removed from slot: " + slot.slotNumber);
        } else {
            System.out.println("Vehicle not found");
        }
    }

    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(5);
        Vehicle car = new Vehicle("KA-01-HH-1234", VehicleType.CAR);
        lot.parkVehicle(car);
        lot.removeVehicle(car);
    }
}
