import java.util.*;

class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class RoomInventory {
    private HashMap<String, Integer> inventory;

    RoomInventory() {
        inventory = new HashMap<>();
        inventory.put("Single Room", 2);
    }

    synchronized boolean allocate(String roomType) {
        if (inventory.getOrDefault(roomType, 0) > 0) {
            inventory.put(roomType, inventory.get(roomType) - 1);
            return true;
        }
        return false;
    }
}

class BookingQueue {
    private Queue<Reservation> queue;

    BookingQueue() {
        queue = new LinkedList<>();
    }

    synchronized void add(Reservation r) {
        queue.add(r);
    }

    synchronized Reservation get() {
        return queue.poll();
    }
}

class BookingProcessor extends Thread {

    private BookingQueue queue;
    private RoomInventory inventory;

    BookingProcessor(BookingQueue queue, RoomInventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {
        while (true) {
            Reservation r;

            synchronized (queue) {
                r = queue.get();
            }

            if (r == null) break;

            if (inventory.allocate(r.roomType)) {
                System.out.println(Thread.currentThread().getName() +
                        " Confirmed: " + r.guestName);
            } else {
                System.out.println(Thread.currentThread().getName() +
                        " Failed: " + r.guestName);
            }
        }
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();
        BookingQueue queue = new BookingQueue();

        queue.add(new Reservation("Kushal", "Single Room"));
        queue.add(new Reservation("Rahul", "Single Room"));
        queue.add(new Reservation("Anu", "Single Room"));

        BookingProcessor t1 = new BookingProcessor(queue, inventory);
        BookingProcessor t2 = new BookingProcessor(queue, inventory);

        t1.start();
        t2.start();
    }
}
