import java.util.*;

class BookingRequest {
    String guestName;
    String roomType;

    public BookingRequest(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class InventoryService {
    private Map<String, Integer> roomInventory;

    public InventoryService(Map<String, Integer> initialInventory) {
        this.roomInventory = new HashMap<>(initialInventory);
    }

    public synchronized boolean isAvailable(String roomType) {
        return roomInventory.getOrDefault(roomType, 0) > 0;
    }

    public synchronized void decrementInventory(String roomType) {
        if (roomInventory.getOrDefault(roomType, 0) > 0) {
            roomInventory.put(roomType, roomInventory.get(roomType) - 1);
        }
    }

    public synchronized int getAvailableCount(String roomType) {
        return roomInventory.getOrDefault(roomType, 0);
    }
}

class BookingService {
    private Queue<BookingRequest> bookingQueue;
    private InventoryService inventoryService;
    private Map<String, Set<String>> allocatedRooms;

    public BookingService(InventoryService inventoryService) {
        this.bookingQueue = new LinkedList<>();
        this.inventoryService = inventoryService;
        this.allocatedRooms = new HashMap<>();
    }

    public void enqueueBooking(BookingRequest request) {
        bookingQueue.offer(request);
        System.out.println("Booking request queued for " + request.guestName + " (" + request.roomType + ")");
    }

    public void processBookings() {
        while (!bookingQueue.isEmpty()) {
            BookingRequest request = bookingQueue.poll();
            allocateRoom(request);
        }
    }

    private void allocateRoom(BookingRequest request) {
        String roomType = request.roomType;

        if (!inventoryService.isAvailable(roomType)) {
            System.out.println("No rooms available for " + request.guestName + " (" + roomType + ")");
            return;
        }

        allocatedRooms.putIfAbsent(roomType, new HashSet<>());

        String roomId;
        do {
            roomId = roomType.substring(0, 2).toUpperCase() + "-" + UUID.randomUUID().toString().substring(0, 8);
        } while (allocatedRooms.get(roomType).contains(roomId));

        allocatedRooms.get(roomType).add(roomId);
        inventoryService.decrementInventory(roomType);

        System.out.println("Reservation confirmed for " + request.guestName + " (" + roomType + ") -> Room ID: " + roomId);
        System.out.println("Remaining " + roomType + " rooms: " + inventoryService.getAvailableCount(roomType));
    }
}

public class UC6ReservationConfirmationRoomAllocation {
    public static void main(String[] args) {
        Map<String, Integer> initialInventory = Map.of(
                "Single", 2,
                "Double", 2,
                "Suite", 1
        );

        InventoryService inventoryService = new InventoryService(initialInventory);
        BookingService bookingService = new BookingService(inventoryService);

        bookingService.enqueueBooking(new BookingRequest("Alice", "Single"));
        bookingService.enqueueBooking(new BookingRequest("Bob", "Double"));
        bookingService.enqueueBooking(new BookingRequest("Charlie", "Single"));
        bookingService.enqueueBooking(new BookingRequest("Diana", "Suite"));
        bookingService.enqueueBooking(new BookingRequest("Eve", "Single"));

        bookingService.processBookings();
    }
}