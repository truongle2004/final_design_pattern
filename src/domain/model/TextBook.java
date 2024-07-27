package domain.model;

public class TextBook extends Book {
    private String status;

    public TextBook(int id, String date, double price, int quantity, String publisher, String status) {
        super(id, date, price, quantity, publisher);
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public double calculateTotalAmount() {
        if (this.status.equals("new")) {
            return getQuantity() * getPrice();
        }
        return getQuantity() * getPrice() * 0.5;
    }
}
