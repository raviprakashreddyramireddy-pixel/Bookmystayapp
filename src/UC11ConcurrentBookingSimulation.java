import java.util.*;

class ConcurrentBookingProcessor {

    private Queue<Reservation> bookingQueue = new LinkedList<>();
    private Map<String,Integer> inventory;

    public ConcurrentBookingProcessor(Map<String,Integer> inventory){
        this.inventory = inventory;
    }

    public synchronized void addBookingRequest(Reservation r){
        bookingQueue.add(r);
    }

    public synchronized Reservation getNextRequest(){
        return bookingQueue.poll();
    }

    public synchronized void processBooking(Reservation r, String roomType){
        int count = inventory.getOrDefault(roomType,0);

        if(count>0){
            inventory.put(roomType,count-1);
            System.out.println(Thread.currentThread().getName()+" booked "+roomType);
        } else {
            System.out.println(Thread.currentThread().getName()+" failed. No "+roomType+" rooms");
        }
    }
}

class BookingWorker extends Thread{

    private ConcurrentBookingProcessor processor;
    private String roomType;

    public BookingWorker(ConcurrentBookingProcessor processor,String roomType){
        this.processor=processor;
        this.roomType=roomType;
    }

    public void run(){
        Reservation r = processor.getNextRequest();
        if(r!=null){
            processor.processBooking(r,roomType);
        }
    }
}

public class UC11ConcurrentBookingSimulation {

    public static void main(String[] args){

        Map<String,Integer> inventory=new HashMap<>();
        inventory.put("Standard",2);
        inventory.put("Deluxe",1);

        ConcurrentBookingProcessor processor =
                new ConcurrentBookingProcessor(inventory);

        processor.addBookingRequest(new Reservation("Alice","Standard"));
        processor.addBookingRequest(new Reservation("Bob","Standard"));
        processor.addBookingRequest(new Reservation("Charlie","Deluxe"));

        Thread t1=new BookingWorker(processor,"Standard");
        Thread t2=new BookingWorker(processor,"Standard");
        Thread t3=new BookingWorker(processor,"Deluxe");

        t1.start();
        t2.start();
        t3.start();
    }
}
