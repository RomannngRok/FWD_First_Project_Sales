package ResourcesPackages.model;
import java.util.ArrayList;

public class InvoiceHeader {
    private int invoiceID;
    private  String customerName;
    private  String  Date;
    private  double Total;
    private ArrayList<InvoiceLine> item;

    public ArrayList<InvoiceLine> getItem() {
        return item;
    }

    public void setItem(ArrayList<InvoiceLine> item) {
        this.item = item;
    }


    public  InvoiceHeader()
    {

    }
    public InvoiceHeader(int invoiceID) {
        this.invoiceID = invoiceID;
    }


    public InvoiceHeader(int invoiceID, String date, String customerName ) {
        this.invoiceID = invoiceID;
        this.customerName = customerName;
        this.Date = date;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double total) {
        Total = total;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }


}
