package ResourcesPackages.model;

public class InvoiceLine extends InvoiceHeader{
    private int itemNumber;
    private  String itemName;
    private double itemPrice;
    private int itemCount;
    public double itemTotal;
    InvoiceHeader invoice1;

    public InvoiceHeader getInvoice1() {
        return invoice1;
    }

    public void setInvoice1(InvoiceHeader invoice1) {
        this.invoice1 = invoice1;
    }

    public InvoiceLine(int itemNumber) {
        super(itemNumber);
    }

    public InvoiceLine(int itemNumber, String itemName, double itemPrice, int itemCount, double itemTotal) {

        this.itemNumber = itemNumber;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemTotal = itemTotal;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    public double getItemTotal() {
        return itemTotal = itemPrice * itemCount;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }
}
