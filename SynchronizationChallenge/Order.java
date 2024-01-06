public class Order {

    private static int count = 1;
    private final int ID;
    private final Shoe shoe;
    private final int qtd;

    public Order(Shoe shoe, int qtd) {
        this.ID = count++;
        this.shoe = shoe;
        this.qtd = qtd;
    }

    @Override
    public String toString() {
        return String.format("%03d - (%d) %s for %.2f",
                ID,
                qtd,
                shoe.name(),
                shoe.value()*qtd);
    }
}

