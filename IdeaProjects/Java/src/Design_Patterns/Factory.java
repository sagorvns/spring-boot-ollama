package Design_Patterns;
//The Factory Pattern is a creational design pattern that provides an interface for
//creating objects but lets subclasses decide which class to instantiate.
//It helps in creating objects without exposing the creation logic.
interface Vehicle { void create(); }

class Car implements Vehicle {
    public void create() { System.out.println("Car created"); }
}

class Bike implements Vehicle {
    public void create() { System.out.println("Bike created"); }
}

class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("Car")) return new Car();
        else if (type.equalsIgnoreCase("Bike")) return new Bike();
        return null;
    }
    public static void main(String[] args) {
        Vehicle car = VehicleFactory.getVehicle("Car");
        car.create(); // Car created
    }
}

