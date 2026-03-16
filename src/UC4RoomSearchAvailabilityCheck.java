public class UC4RoomSearchAvailabilityCheck {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System - Version 4.0\n");

        // Use the existing inventory from Use Case 3
        RoomInventory inventory = new RoomInventory();

        System.out.println("Available Rooms:\n");

        // Create Room objects (reuse previous Room classes)
        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Only display rooms with availability > 0
        if (inventory.getAvailability("Single Room") > 0) {
            single.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Single Room"));
            System.out.println();
        }

        if (inventory.getAvailability("Double Room") > 0) {
            doubleRoom.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Double Room"));
            System.out.println();
        }

        if (inventory.getAvailability("Suite Room") > 0) {
            suite.displayRoomDetails();
            System.out.println("Available: " + inventory.getAvailability("Suite Room"));
            System.out.println();
        }
    }
}