import java.util.LinkedList;
import java.util.Queue;

// Represents a guest's booking request
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    void displayRequest() {
        System.out.println("Guest: " + guestName + ", Room Type: " + roomType);
    }
}

// Manages booking requests in a FIFO queue
class BookingRequestQueue {
    private Queue<Reservation> queue;

    BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    void addRequest(Reservation request) {
        queue.add(request);
    }

    void displayAllRequests() {
        System.out.println("Current Booking Requests in Queue:\n");
        for (Reservation r : queue) {
            r.displayRequest();
        }
    }

    int getQueueSize() {
        return queue.size();
    }
}

// Main public class for Use Case 5
public class UC5BookingRequest {

    public static void main(String[] args) {

        System.out.println("Hotel Booking System - Version 5.0\n");

        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate incoming booking requests
        bookingQueue.addRequest(new Reservation("Alice", "Single Room"));
        bookingQueue.addRequest(new Reservation("Bob", "Double Room"));
        bookingQueue.addRequest(new Reservation("Charlie", "Suite Room"));
        bookingQueue.addRequest(new Reservation("Diana", "Single Room"));

        // Display queued requests
        bookingQueue.displayAllRequests();

        System.out.println("\nTotal Requests in Queue: " + bookingQueue.getQueueSize());
    }
}