abstract class Room {
    String roomType;
    int beds;
    double size;
    double price;

    Room(String roomType, int beds, double size, double price) {
        this.roomType = roomType;
        this.beds = beds;
        this.size = size;
        this.price = price;
    }

    void displayRoomDetails() {
        System.out.println("Room Type: " + roomType);
        System.out.println("Beds: " + beds);
        System.out.println("Size: " + size + " sqm");
        System.out.println("Price per night: $" + price);
    }
}

class SingleRoom extends Room {
    SingleRoom() {
        super("Single Room", 1, 20.0, 100.0);
    }
}

class DoubleRoom extends Room {
    DoubleRoom() {
        super("Double Room", 2, 35.0, 180.0);
    }
}

class SuiteRoom extends Room {
    SuiteRoom() {
        super("Suite Room", 3, 60.0, 350.0);
    }
}

public class UC2BasicRoomTypesStaticAvailability {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System - Version 2.1");

        Room single = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suite = new SuiteRoom();

        int singleAvailability = 5;
        int doubleAvailability = 3;
        int suiteAvailability = 2;

        System.out.println("\n--- Single Room ---");
        single.displayRoomDetails();
        System.out.println("Available: " + singleAvailability);

        System.out.println("\n--- Double Room ---");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + doubleAvailability);

        System.out.println("\n--- Suite Room ---");
        suite.displayRoomDetails();
        System.out.println("Available: " + suiteAvailability);
    }
}