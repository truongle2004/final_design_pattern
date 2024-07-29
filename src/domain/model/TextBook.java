package domain.model;

public class TextBook extends Book {

    private String status;

    public TextBook(int id, String date, double price, int quantity, String publisher, String status) {
        super(id, date, price, quantity, publisher);
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }

    @Override
    public double calculateTotalAmount() {
        if (getStatus() == "new") {
            return getPrice() * getQuantity();
        }
        return getPrice() * getQuantity() * 0.5;
    }
}
