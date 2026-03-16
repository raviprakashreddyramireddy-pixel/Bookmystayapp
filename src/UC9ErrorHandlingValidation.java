import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class InvalidBookingValidator {

    private static final List<String> validRoomTypes =
            Arrays.asList("Standard", "Deluxe", "Suite");

    public static void validate(String guestName, String roomType, int availableRooms)
            throws InvalidBookingException {

        if (guestName == null || guestName.trim().isEmpty()) {
            throw new InvalidBookingException("Guest name cannot be empty");
        }

        if (!validRoomTypes.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }

        if (availableRooms <= 0) {
            throw new InvalidBookingException("No rooms available for booking");
        }
    }
}

public class UC9ErrorHandlingValidation {

    public static void main(String[] args) {

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 2);
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 0);

        String guestName = "Alice";
        String roomType = "Suite";

        try {

            int available = inventory.getOrDefault(roomType, -1);

            InvalidBookingValidator.validate(guestName, roomType, available);

            Reservation reservation = new Reservation(guestName, roomType);

            inventory.put(roomType, available - 1);

            System.out.println("Booking successful: " + reservation);

        } catch (InvalidBookingException e) {

            System.out.println("Booking failed: " + e.getMessage());

        }

        System.out.println("System continues running normally.");
    }
}
