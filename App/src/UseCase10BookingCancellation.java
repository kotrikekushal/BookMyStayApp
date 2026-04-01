import java.util.*;

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void decrease(String roomType) {
        inventory.put(roomType, inventory.get(roomType) - 1);
    }

    void increase(String roomType) {
        inventory.put(roomType, inventory.get(roomType) + 1);
    }
}

class BookingService {

    private RoomInventory inventory;
    private HashMap<String, String> bookings;
    private Stack<String> rollbackStack;
    private int counter = 1;

    BookingService(RoomInventory inventory) {
        this.inventory = inventory;
        bookings = new HashMap<>();
        rollbackStack = new Stack<>();
    }

    String book(String guest, String roomType) {
        if (inventory.getAvailability(roomType) > 0) {
            String roomId = roomType + "-" + counter++;
            bookings.put(roomId, roomType);
            inventory.decrease(roomType);
            return roomId;
        }
        return null;
    }

    void cancel(String roomId) {
        if (!bookings.containsKey(roomId)) {
            System.out.println("Invalid cancellation");
            return;
        }

        String roomType = bookings.get(roomId);

        rollbackStack.push(roomId);
        bookings.remove(roomId);
        inventory.increase(roomType);

        System.out.println("Cancelled: " + roomId);
    }

    void showStack() {
        System.out.println("Rollback Stack: " + rollbackStack);
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingService service = new BookingService(inventory);

        String id1 = service.book("Kushal", "Single Room");
        System.out.println("Booked: " + id1);

        service.cancel(id1);

        service.showStack();
    }
}