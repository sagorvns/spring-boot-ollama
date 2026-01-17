package System_Design;

import java.util.*;

public class VendingMachine {
    // Item inner class
    static class Item {
        String name;
        int price;
        Item(String name, int price) {
            this.name = name;
            this.price = price;
        }
    }

    // Machine state
    enum State {
        IDLE,
        WAITING_FOR_MONEY,
        DISPENSING
    }

    // Machine data
    private Map<Integer, Item> inventory = new HashMap<>();
    private State state = State.IDLE;
    private Item selectedItem = null;
    private int currentBalance = 0;

    // Constructor
    public VendingMachine() {
        inventory.put(1, new Item("Coke", 30));
        inventory.put(2, new Item("Pepsi", 35));
        inventory.put(3, new Item("Water", 20));
    }

    // Display available items
    public void displayItems() {
        System.out.println("Available Items:");
        for (Map.Entry<Integer, Item> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name + " - ₹" + entry.getValue().price);
        }
    }

    // Select an item
    public void selectItem(int code) {
        if (!inventory.containsKey(code)) {
            System.out.println("Invalid selection.");
            return;
        }
        selectedItem = inventory.get(code);
        state = State.WAITING_FOR_MONEY;
        System.out.println("Selected: " + selectedItem.name + " - ₹" + selectedItem.price);
    }

    // Insert money
    public void insertMoney(int amount) {
        if (state != State.WAITING_FOR_MONEY) {
            System.out.println("Please select an item first.");
            return;
        }

        currentBalance += amount;
        System.out.println("Inserted: ₹" + amount + " | Total: ₹" + currentBalance);

        if (currentBalance >= selectedItem.price) {
            dispense();
        } else {
            System.out.println("Please insert ₹" + (selectedItem.price - currentBalance) + " more.");
        }
    }

    // Dispense item
    private void dispense() {
        state = State.DISPENSING;
        System.out.println("Dispensing: " + selectedItem.name);
        int change = currentBalance - selectedItem.price;
        if (change > 0) {
            System.out.println("Returning change: ₹" + change);
        }
        reset();
    }

    // Reset machine
    private void reset() {
        state = State.IDLE;
        selectedItem = null;
        currentBalance = 0;
        System.out.println("Thank you! Machine is ready for next customer.\n");
    }

    // Main method to demo
    public static void main(String[] args) {
        VendingMachine vm = new VendingMachine();
        vm.displayItems();

        vm.selectItem(2);       // Pepsi
        vm.insertMoney(10);     // Insert ₹10
        vm.insertMoney(10);     // Insert ₹10
        vm.insertMoney(20);     // Insert ₹20, total ₹40
    }
}