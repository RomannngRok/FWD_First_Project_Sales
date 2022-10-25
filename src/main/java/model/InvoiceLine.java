package model;

public class InvoiceLine {
    //Create variables invNum, name, count,and the price
    private String name;
    private int count;
    private double price;
    private InvoiceHeader inv;

    // create constructor
    public InvoiceLine(String name, int count, double price, InvoiceHeader inv) {
        this.name = name;
        this.count = count;
        this.price = price;
        this.inv = inv;
    }

    //Calculate item total price method
    public double getTotal() {
        return count * price;
    }


    //Create Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public InvoiceHeader getInv() {
        return inv;
    }

    public void setInv(InvoiceHeader inv) {
        this.inv = inv;
    }

    //Create to string method to print the all data


    @Override
    public String toString() {
        return "InvoiceLine{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
}