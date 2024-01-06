import java.util.ArrayList;
import java.util.List;

public class ShoeWarehouse {

    public static final List<Shoe> shoes = new ArrayList<>(32);
    private final List<Order> orders = new ArrayList<>(32);

    public synchronized void receiveOrder(Order order){

        try{
            while(orders.size() >= 5){
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("\tClient order %s\n", order.toString());
        orders.add(order);
        notifyAll();
    }

    public synchronized void fulfillOrder(String consumer){
        try{
            while(orders.isEmpty()){
                wait();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Order order = orders.getFirst();
        orders.removeFirst();
        System.out.printf("\t%s serve %s\n", consumer, order.toString());
        notifyAll();
    }

}
