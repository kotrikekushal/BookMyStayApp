import java.util.HashMap;

class RoomInventory {

    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    void displayInventory() {
        System.out.println("=== Room Inventory ===");
        for (String roomType : inventory.keySet()) {
            System.out.println(roomType + " : " + inventory.get(roomType));
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        System.out.println();

        System.out.println("Single Room Available: " +
                inventory.getAvailability("Single Room"));

        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nAfter Update:");
        inventory.displayInventory();
    }
}
