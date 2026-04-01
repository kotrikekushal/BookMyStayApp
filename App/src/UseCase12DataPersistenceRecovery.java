import java.io.*;
import java.util.*;

class SystemState implements Serializable {
    HashMap<String, Integer> inventory;
    List<String> bookings;

    SystemState(HashMap<String, Integer> inventory, List<String> bookings) {
        this.inventory = inventory;
        this.bookings = bookings;
    }
}

class PersistenceService {

    void save(SystemState state) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.ser"));
            out.writeObject(state);
            out.close();
            System.out.println("State saved");
        } catch (Exception e) {
            System.out.println("Save error");
        }
    }

    SystemState load() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.ser"));
            SystemState state = (SystemState) in.readObject();
            in.close();
            System.out.println("State loaded");
            return state;
        } catch (Exception e) {
            System.out.println("No previous data found");
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {

        PersistenceService service = new PersistenceService();

        SystemState state = service.load();

        if (state == null) {
            HashMap<String, Integer> inventory = new HashMap<>();
            inventory.put("Single Room", 2);

            List<String> bookings = new ArrayList<>();
            bookings.add("Single Room-1");

            state = new SystemState(inventory, bookings);
        }

        System.out.println("Inventory: " + state.inventory);
        System.out.println("Bookings: " + state.bookings);

        service.save(state);
    }
}