package ResourcesPackages.view;


import ResourcesPackages.model.InvoiceLine;

import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class LineDialog extends DefaultTableModel {
    private final String[] columnNames = { "Invoice ID", "Item Name", "Item Price", "Count" , "Item Total"};

    private ArrayList<InvoiceLine> rows ;

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
        InvoiceLine item= rows.get(row);
        switch (col)
        {
            case 0:
                return item.getInvoiceID();
            case 1:
                return item.getCustomerName();
            case 2:
                return item.getDate();
        }
        return  null;
    }
}
