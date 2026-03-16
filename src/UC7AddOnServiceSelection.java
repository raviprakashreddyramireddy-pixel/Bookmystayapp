import java.util.*;

class AddOnService {
    String serviceName;
    double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> reservationServices;

    public AddOnServiceManager() {
        this.reservationServices = new HashMap<>();
    }

    public void addServiceToReservation(String reservationId, AddOnService service) {
        reservationServices.putIfAbsent(reservationId, new ArrayList<>());
        reservationServices.get(reservationId).add(service);
        System.out.println("Added service " + service.serviceName + " ($" + service.cost + ") to reservation " + reservationId);
    }

    public double calculateTotalCost(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, Collections.emptyList());
        double total = 0;
        for (AddOnService s : services) {
            total += s.cost;
        }
        return total;
    }

    public void listServices(String reservationId) {
        List<AddOnService> services = reservationServices.getOrDefault(reservationId, Collections.emptyList());
        if (services.isEmpty()) {
            System.out.println("No add-on services for reservation " + reservationId);
            return;
        }
        System.out.println("Add-on services for reservation " + reservationId + ":");
        for (AddOnService s : services) {
            System.out.println("- " + s.serviceName + " ($" + s.cost + ")");
        }
        System.out.println("Total additional cost: $" + calculateTotalCost(reservationId));
    }
}

class BookingServiceUC7 {
    private Map<String, String> confirmedReservations;

    public BookingServiceUC7() {
        confirmedReservations = new HashMap<>();
    }

    public String confirmReservation(String guestName) {
        String reservationId = "RES-" + UUID.randomUUID().toString().substring(0, 8);
        confirmedReservations.put(reservationId, guestName);
        System.out.println("Reservation confirmed for " + guestName + " -> ID: " + reservationId);
        return reservationId;
    }
}

public class UC7AddOnServiceSelection {
    public static void main(String[] args) {
        BookingServiceUC7 bookingService = new BookingServiceUC7();
        AddOnServiceManager addOnManager = new AddOnServiceManager();

        String res1 = bookingService.confirmReservation("Alice");
        String res2 = bookingService.confirmReservation("Bob");

        AddOnService breakfast = new AddOnService("Breakfast", 15.0);
        AddOnService airportPickup = new AddOnService("Airport Pickup", 25.0);
        AddOnService spa = new AddOnService("Spa Access", 50.0);

        addOnManager.addServiceToReservation(res1, breakfast);
        addOnManager.addServiceToReservation(res1, spa);
        addOnManager.addServiceToReservation(res2, airportPickup);

        addOnManager.listServices(res1);
        addOnManager.listServices(res2);

        String res3 = bookingService.confirmReservation("Charlie");
        addOnManager.listServices(res3);
    }
}
