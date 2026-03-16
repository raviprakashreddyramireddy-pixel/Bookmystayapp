import java.util.*;

class CancellationService {

    private Map<String, Integer> inventory;
    private Stack<String> rollbackStack = new Stack<>();
    private Map<Reservation, String> reservationRoomMap;

    public CancellationService(Map<String, Integer> inventory, Map<Reservation, String> reservationRoomMap) {
        this.inventory = inventory;
        this.reservationRoomMap = reservationRoomMap;
    }

    public void cancelReservation(Reservation reservation) {

        if (!reservationRoomMap.containsKey(reservation)) {
            System.out.println("Cancellation failed: Reservation not found");
            return;
        }

        String roomType = reservationRoomMap.get(reservation);

        rollbackStack.push(roomType);

        int count = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, count + 1);

        reservationRoomMap.remove(reservation);

        System.out.println("Cancellation successful");
    }

    public Stack<String> getRollbackStack() {
        return rollbackStack;
    }
}

public class UC10BookingCancellationInventoryRollback {

    public static void main(String[] args) {

        Map<String, Integer> inventory = new HashMap<>();
        inventory.put("Standard", 1);
        inventory.put("Deluxe", 1);
        inventory.put("Suite", 1);

        Map<Reservation, String> reservationRoomMap = new HashMap<>();

        Reservation r1 = new Reservation("Alice", "Deluxe");

        reservationRoomMap.put(r1, "Deluxe");
        inventory.put("Deluxe", inventory.get("Deluxe") - 1);

        CancellationService service = new CancellationService(inventory, reservationRoomMap);

        service.cancelReservation(r1);

        System.out.println("Rollback Stack: " + service.getRollbackStack());
        System.out.println("Inventory: " + inventory);
    }
}