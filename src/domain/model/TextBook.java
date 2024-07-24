package domain.model;

public class TextBook extends Book {
    private String status;

    public TextBook(int id, int day, int month, int year, double price, int quantity, int publisher, String status,
            double totalAmount) {
        super(id, day, month, year, price, quantity, publisher, totalAmount);
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
        if (this.status == "new") {
            return getQuantity() * getPrice();
        }
        return getQuantity() * getPrice() * (50 / 100);
    }
}
