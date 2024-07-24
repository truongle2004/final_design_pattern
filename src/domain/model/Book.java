package domain.model;

public abstract class Book {
    private int id;
    private int day;
    private int month;
    private int year;
    private double price;
    private int quantity;
    private int publisher;
    private double totalAmount;

    public Book(int id, int day, int month, int year, double price, int quantity, int publisher, double totalAmount) {
        this.id = id;
        this.day = day;
        this.month = month;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.publisher = publisher;
        this.totalAmount = totalAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
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

    public int getPublisher() {
        return publisher;
    }

    public void setPublisher(int publisher) {
        this.publisher = publisher;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    public abstract double calculateTotalAmount();

}
