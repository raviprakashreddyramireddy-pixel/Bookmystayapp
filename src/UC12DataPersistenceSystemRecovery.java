import java.io.*;
import java.util.*;

class PersistenceService {

    private static final String FILE="system_state.dat";

    public void saveState(Map<String,Integer> inventory, List<Reservation> bookings){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE));
            out.writeObject(inventory);
            out.writeObject(bookings);
            out.close();
            System.out.println("State saved");
        }catch(Exception e){
            System.out.println("Save failed");
        }
    }

    public Object[] loadState(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE));
            Map<String,Integer> inventory = (Map<String,Integer>)in.readObject();
            List<Reservation> bookings = (List<Reservation>)in.readObject();
            in.close();
            System.out.println("State restored");
            return new Object[]{inventory, bookings};
        }catch(Exception e){
            System.out.println("No previous state");
            return new Object[]{new HashMap<String,Integer>(), new ArrayList<Reservation>()};
        }
    }
}

public class UC12DataPersistenceSystemRecovery {

    public static void main(String[] args){

        PersistenceService service = new PersistenceService();

        Object[] data = service.loadState();

        Map<String,Integer> inventory = (Map<String,Integer>)data[0];
        List<Reservation> bookings = (List<Reservation>)data[1];

        if(inventory.isEmpty()){
            inventory.put("Standard",3);
            inventory.put("Deluxe",2);
        }

        bookings.add(new Reservation("Alice","Standard"));

        service.saveState(inventory,bookings);

        System.out.println("Inventory: "+inventory);
        System.out.println("Bookings: "+bookings.size());
    }
}
