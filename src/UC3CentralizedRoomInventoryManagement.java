import java.util.HashMap;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    void displayInventory() {
        System.out.println("Current Room Inventory:");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " Available: " + inventory.get(roomType));
        }
    }
}

public class UC3CentralizedRoomInventoryManagement {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System - Version 3.1");

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println("\nUpdating availability...");

        inventory.updateAvailability("Single Room", 4);
        inventory.updateAvailability("Double Room", 2);

        System.out.println("\nUpdated Inventory:");
        inventory.displayInventory();
    }
}