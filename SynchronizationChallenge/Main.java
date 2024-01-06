import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] randName = {"NIKE", "ADIDAS", "NEW BALANCE", "PUMA", "TOPPER"};
        double[] randPrice = {129.90, 259.90, 99.90, 339.90, 669.90};
        Random r = new Random();
        ShoeWarehouse store = new ShoeWarehouse();

        for(int i = 0 ; i < 20; i++){
            ShoeWarehouse.shoes.add(
                    new Shoe(
                            randName[r.nextInt(5)],
                            randPrice[r.nextInt(5)]));
        }

        Thread producer = new Thread(() -> {
            for(int i = 0; i < 10; i++){
                store.receiveOrder(new Order(ShoeWarehouse.shoes.get(
                        r.nextInt(ShoeWarehouse.shoes.size())), r.nextInt(1, 11)));
            }
        });
        Thread consumer1 = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                store.fulfillOrder("John");
            }
        });
        Thread consumer2 = new Thread(() -> {
            for(int i = 0; i < 5; i++){
                store.fulfillOrder("Mary");
            }
        });

        producer.start();
        consumer1.start();
        consumer2.start();
    }
}
