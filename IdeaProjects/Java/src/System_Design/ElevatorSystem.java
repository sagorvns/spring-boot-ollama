package System_Design;

import java.util.PriorityQueue;

class Elevator {
    int currentFloor = 0;
    PriorityQueue<Integer> requests = new PriorityQueue<>();

    public void addRequest(int floor) {
        requests.add(floor);
    }

    public void move() {
        while (!requests.isEmpty()) {
            int targetFloor = requests.poll();
            System.out.println("Moving to floor " + targetFloor);
            currentFloor = targetFloor;
        }
    }
}

public class ElevatorSystem {
    public static void main(String[] args) {
        Elevator elevator = new Elevator();
        elevator.addRequest(5);
        elevator.addRequest(2);
        elevator.move();
    }
}

