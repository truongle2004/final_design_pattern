package domain.model;

public class ReferenceBook extends Book {
    private double tax;

    public ReferenceBook(int id, int day, int month, int year, double price, int quantity, int publisher, double tax,
            double totalAmount) {
        super(id, day, month, year, price, quantity, publisher, totalAmount);
        this.tax = tax;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    @Override
    public double calculateTotalAmount() {
        return (getPrice() * getQuantity()) + getTax();
    }

}
