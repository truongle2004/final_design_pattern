package domain.model;

public class ReferenceBook extends Book {
    private double tax;

    public ReferenceBook(int id, String date, double price, int quantity, String publisher, double tax) {
        super(id, date, price, quantity, publisher);
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
