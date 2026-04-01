import java.util.*;

class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {

    private HashMap<String, List<Service>> serviceMap;

    AddOnServiceManager() {
        serviceMap = new HashMap<>();
    }

    void addService(String reservationId, Service service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);
    }

    void displayServices(String reservationId) {
        List<Service> services = serviceMap.get(reservationId);

        if (services == null) {
            System.out.println("No services added.");
            return;
        }

        double total = 0;

        System.out.println("Services for " + reservationId + ":");
        for (Service s : services) {
            System.out.println(s.name + " - " + s.cost);
            total += s.cost;
        }

        System.out.println("Total Add-On Cost: " + total);
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "Single Room-1";

        manager.addService(reservationId, new Service("Breakfast", 200));
        manager.addService(reservationId, new Service("WiFi", 100));
        manager.addService(reservationId, new Service("Airport Pickup", 500));

        manager.displayServices(reservationId);
    }
}