import java.util.*;

class Reservation {
    String guestName;
    String roomId;

    Reservation(String guestName, String roomId) {
        this.guestName = guestName;
        this.roomId = roomId;
    }
}

class BookingHistory {
    private List<Reservation> history;

    BookingHistory() {
        history = new ArrayList<>();
    }

    void addReservation(Reservation r) {
        history.add(r);
    }

    List<Reservation> getHistory() {
        return history;
    }
}

class BookingReportService {

    void generateReport(List<Reservation> history) {
        System.out.println("=== Booking History ===");

        for (Reservation r : history) {
            System.out.println("Guest: " + r.guestName + ", Room: " + r.roomId);
        }

        System.out.println("\nTotal Bookings: " + history.size());
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {

        BookingHistory history = new BookingHistory();

        history.addReservation(new Reservation("Kushal", "Single Room-1"));
        history.addReservation(new Reservation("Rahul", "Double Room-1"));
        history.addReservation(new Reservation("Anu", "Suite Room-1"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history.getHistory());
    }
}
