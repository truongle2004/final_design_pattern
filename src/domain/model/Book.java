package domain.model;

public abstract class Book {
    private int id;
    private String date;
    private double price;
    private int quantity;
    private String publisher;

    public Book(int id, String date, double price, int quantity, String publisher) {
        this.id = id;
        this.date = date;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public abstract double calculateTotalAmount();

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
