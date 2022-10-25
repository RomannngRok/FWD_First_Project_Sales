package model;

import java.util.ArrayList;
import java.util.Date;

public class InvoiceHeader {

    //Create variables num, date, and the customer name
    private int num;
    private Date date;
    private String name;
    private ArrayList<InvoiceLine> lines;


    //Create constructor
    public InvoiceHeader(int num,Date date,String name){
        this.num=num;
        this.date=date;
        this.name = name;
    }

    //Calculate invoice total price method
    public double getTotal() {
        double total=0.0;
        for (InvoiceLine line : lines) {
            total += line.getTotal();
        }
        return total;

    }

    // create Getter and Setter methods
    public ArrayList<InvoiceLine> getLines() {
        return lines;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Create to string method to print the all data
    @Override
    public String toString() {
        return "InvoiceHeader{" +
                "num=" + num +
                ", date=" + date +
                ", name='" + name + '\'' +
                '}';
    }

}
