package ResourcesPackages.view;

import ResourcesPackages.model.InvoiceHeader;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class InvoiceDialog extends DefaultTableModel {

    private final String[] columnNames = { "Invoice ID", "Date", "Customer", "Total" };
    private ArrayList<InvoiceHeader> rows ;

    public ArrayList<InvoiceHeader> getRows() {
        return rows;
    }

    public void setRows(ArrayList<InvoiceHeader> rows) {
        this.rows = rows;
    }

    @Override
    public int getRowCount() {
        return super.getRowCount();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int row, int col) {
        InvoiceHeader INV= rows.get(row);
        switch (col)
        {
            case 0:
                return INV.getInvoiceID();
            case 1:
                return INV.getCustomerName();
            case 2:
                return INV.getDate();
        }
        return  null;
    }
}
