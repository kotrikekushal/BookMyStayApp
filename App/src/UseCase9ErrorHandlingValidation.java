import java.util.HashMap;

class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 1);
        inventory.put("Double Room", 1);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, -1);
    }

    void bookRoom(String roomType) throws InvalidBookingException {

        if (!inventory.containsKey(roomType)) {
            throw new InvalidBookingException("Invalid Room Type");
        }

        if (inventory.get(roomType) <= 0) {
            throw new InvalidBookingException("No rooms available");
        }

        inventory.put(roomType, inventory.get(roomType) - 1);
    }
}

public class UseCase9ErrorHandlingValidation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        try {
            inventory.bookRoom("Single Room");
            System.out.println("Booking Successful");

            inventory.bookRoom("Single Room");
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }

        try {
            inventory.bookRoom("Luxury Room");
        } catch (InvalidBookingException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}