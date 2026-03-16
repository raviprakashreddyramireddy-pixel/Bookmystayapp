import java.util.*;

class BookingHistory {
    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public List<Reservation> getReservations() {
        return reservations;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation> reservations) {
        System.out.println("Booking History Report");
        for (Reservation r : reservations) {
            System.out.println(r);
        }
        System.out.println("Total Reservations: " + reservations.size());
    }
}

public class UC8BookingHistoryReporting {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        Reservation r1 = new Reservation("Alice", "Deluxe");
        Reservation r2 = new Reservation("Bob", "Suite");
        Reservation r3 = new Reservation("Charlie", "Standard");

        history.addReservation(r1);
        history.addReservation(r2);
        history.addReservation(r3);

        BookingReportService reportService = new BookingReportService();
        reportService.generateReport(history.getReservations());
    }
}