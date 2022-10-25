package ResourcesPackages.controller;
import ResourcesPackages.model.InvoiceHeader;
import ResourcesPackages.model.InvoiceLine;
import ResourcesPackages.view.LineDialog;
import ResourcesPackages.view.InvoiceDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class InvController extends JFrame implements ActionListener, ListSelectionListener {

    public SimpleDateFormat df= new SimpleDateFormat("dd-MM-yyyy");
    private  ArrayList<InvoiceHeader> Invoices= new ArrayList<>();

    private final ArrayList<String[]> Rs = new ArrayList<>();
    private final ArrayList<String[]> ItemsRs = new ArrayList<>();
    private final String[] columnNames = { "Invoice ID", "Date", "Amount", "Total" };
    private final String[] columnItemNames = { "Invoice ID", "Item Name", "Item Price", "Count" , " Total"};

    Boolean itemFileFull= false;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem OpenItem;
    private JMenuItem saveItem;
    private JMenuItem exitItem;

    Container contentPanel;
    private JPanel Panel1;
    private JPanel Panel2;
    private JPanel Panel3;
    private JPanel Panel4;
    private JPanel buttonPanel;
    private JPanel buttonPanel1;
    private JPanel buttonPanel2;

    private JButton newBtn, saveBtn , deleteBtn , cancelBn;
    private JTable invoiceTable;
    private JTable itemTable;
    private JLabel label, label2, label3, numlabel;
    private JTextField dateField, nameField;
    private String oldNameText;
    private String oldDateText;

    // Components of The popup panel
    private JLabel idLabel;
    private JTextField idField1;
    private JLabel nameLabel;
    private JTextField nameField1;
    private JLabel dateLabel;
    private JTextField dateField1;
    private JLabel itemNameLabel;
    private JTextField itemNameTxtBox;
    private JLabel itemPriceLabel;
    private JTextField itemPriceTxtBox;

    private JLabel countLabel;
    private JTextField countTxtBox;
    private JLabel totalLabel;
    private JTextField totlaTxtBox;
    private JPanel newPanel;

    public InvController() {
        super(" The Sales Invoice System");
        setSize(850, 550);
        setResizable(false);
        setLocation(60, 60);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        contentPanel = getContentPane();

        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        OpenItem = new JMenuItem("Load");
        saveItem = new JMenuItem("Save");
        exitItem = new JMenuItem("EXit");
        
        fileMenu.add(OpenItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        this.invoiceTable = new JTable(new InvoiceDialog());
        this.itemTable = new JTable(new LineDialog());
        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        invoiceTable.getSelectionModel().addListSelectionListener(this);

        Panel1 = new JPanel();
        Panel2 = new JPanel();
        Panel2.setLayout(new BoxLayout(Panel2, BoxLayout.Y_AXIS));
        Panel3 = new JPanel(new GridLayout());
        Panel4 = new JPanel();
        Panel4.setLayout(new BoxLayout(Panel4, BoxLayout.Y_AXIS));
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout());
        buttonPanel1 = new JPanel();
        buttonPanel2 = new JPanel();


        label = new JLabel("Name: ");
        nameField = new JTextField(10);
        label2 = new JLabel("Date: ");
        dateField = new JTextField(10);
        label3 = new JLabel("Invoice ID: ");
        numlabel = new JLabel("   ");

        //The Buttons on The Frame
        newBtn = new JButton(" Create a New Invoice");
        newBtn.setActionCommand("new");
        deleteBtn = new JButton(" Delete Invoice");
        deleteBtn.setActionCommand("delete");
        saveBtn = new JButton(" Save Changes");
        saveBtn.setActionCommand("save");
        cancelBn = new JButton(" cancel");
        cancelBn.setActionCommand("cancel");
        Panel1.setBorder(BorderFactory.createTitledBorder(" Invoice Header"));
        Panel3.setBorder(BorderFactory.createTitledBorder(" Invoice Lines"));

        //table2= new JTable(values2, col2);
        Panel1.add(new JScrollPane(invoiceTable));
        Panel4.add(numlabel);
        Panel4.add(dateField);
        Panel4.add(label3);
        Panel4.add(label);
        Panel4.add(nameField);
        Panel4.add(label2);
        Panel3.add(new JScrollPane(itemTable));
        Panel2.add(Panel4);
        Panel2.add(Panel3);
        buttonPanel1.add(newBtn);
        buttonPanel2.add(saveBtn);
        buttonPanel1.add(cancelBn);
        buttonPanel2.add(deleteBtn);
        buttonPanel.add(buttonPanel1);
        buttonPanel.add(buttonPanel2);
        contentPanel.add(new JScrollPane(Panel1));
        contentPanel.add(Panel2, BorderLayout.EAST);
        contentPanel.add(buttonPanel, BorderLayout.PAGE_END);


        // Action listener of buttons
        OpenItem.addActionListener((new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadHeaderFile();
            }
        }));

        exitItem.addActionListener((new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //exit
                System.exit(0);
            }
        }));
        saveItem.addActionListener((new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveFile();
            }
        }));
        newBtn.addActionListener(this);
        saveBtn.addActionListener(this);
        cancelBn.addActionListener(this);
        deleteBtn.addActionListener(this);



        // The panel of (add new invoice)
        newPanel= new JPanel(new GridLayout(0,1));
        newPanel.setBorder(BorderFactory.createTitledBorder(" Add new Invoice Header"));

        idLabel = new JLabel("ID: ");
        idField1 = new JTextField(25);
        nameLabel = new JLabel("Name:  ");
        nameField1 = new JTextField(25);
        itemNameLabel = new JLabel("Item Name: ");
        itemNameTxtBox=new JTextField(25);
        dateLabel = new JLabel("Date:  ");
        dateField1 = new JTextField(25);
        itemPriceLabel=new JLabel("Item Price: ");
        itemPriceTxtBox= new JTextField(25);
        countLabel= new JLabel(" count:  ");
        countTxtBox= new JTextField(25);
        totalLabel= new JLabel(" Total :");
        totlaTxtBox= new JTextField(25);
        totlaTxtBox.setEditable(false);
        newPanel.add(idLabel);
        newPanel.add(idField1);
        newPanel.add(nameLabel);
        newPanel.add(nameField1);
        newPanel.add(dateLabel);
        newPanel.add(dateField1);
        newPanel.add(itemNameLabel);
        newPanel.add(itemNameTxtBox);
        newPanel.add(itemPriceLabel);
        newPanel.add(itemPriceTxtBox);
        newPanel.add(countLabel);
        newPanel.add(countTxtBox);
        newPanel.add(totalLabel);
        newPanel.add(totlaTxtBox);

        //this.loadHeaderFile();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {getSelectedRowValues();}

    @Override
    public void actionPerformed(ActionEvent e) {
        // load Files in tables
        if ( e.getActionCommand().equals("L")) {
            loadHeaderFile();}

        // SAVE the tables into csv files
        if ( e.getActionCommand().equals("S")) {

            JOptionPane.showMessageDialog(this, "Save Invoice Header Table ",
                    " Save Invoice Header", JOptionPane.INFORMATION_MESSAGE);
            ExportTableToCSV(invoiceTable);

            JOptionPane.showMessageDialog(this, "Save Invoice Lines Table ",
                    " Save Invoice Lines", JOptionPane.INFORMATION_MESSAGE);
            ExportTableToCSV(itemTable);

            JOptionPane.showMessageDialog(this, "Files are saved Successfully",
                    " Success ", JOptionPane.INFORMATION_MESSAGE);


        }
        //Create Row
        if ( e.getActionCommand().equals("new")) {
            idField1.setText("");
            nameField1.setText("");
            dateField1.setText("");
            itemNameTxtBox.setText("");
            itemPriceTxtBox.setText("");
            countTxtBox.setText("");
            createNewInvoice();


        }

        //Delete a row from the Lines table
        if ( e.getActionCommand().equals("delete"))
        {DeleteSelectedRow(itemTable);}

        //save changes of invoice row
        if ( e.getActionCommand().equals("save"))
        { saveChangesOfInvoice(); }

        // Cancel the Changes
        if ( e.getActionCommand().equals("cancel"))
        { cancelChanges();}

        // exit
        if(e.getActionCommand().equals("E"))
        { System.exit(0); }

    }

    // save the changes in the header table
    private void cancelChanges()
    {
        nameField.setText(oldNameText);
        dateField.setText(oldDateText);
    }

    // display the add-new-invoice popup before adding a new entry to the header table
    private void createNewInvoice()
    {
        if( itemFileFull==true)
        {
            int optionType = JOptionPane.DEFAULT_OPTION;
            int messageType = JOptionPane.PLAIN_MESSAGE;
            String[] options = { "Save", "Cancel" };
            Object initialValue = options[0];
            int res= JOptionPane.showOptionDialog(null, newPanel,
                    "Add new invoice", optionType, messageType, null, options,
                    initialValue);

            if ( res==JOptionPane.YES_OPTION)
            {
                String id=idField1.getText();
                String newDATE= dateField1.getText();
                String name1= nameField1.getText();
                String itemName= itemNameTxtBox.getText();
                double itemPrice= Double.parseDouble(itemPriceTxtBox.getText());
                int count= Integer.parseInt(countTxtBox.getText());
                double total=itemPrice* count;

                DefaultTableModel headerRow= (DefaultTableModel)invoiceTable.getModel();
                DefaultTableModel invoiceRow= (DefaultTableModel)itemTable.getModel();

                headerRow.addRow(new Object[]{id, newDATE,name1});
                invoiceRow.addRow(new Object[]{id, itemName,itemPrice,count,total});
                JOptionPane.showMessageDialog(this, "New row is added Successfully to invoice Header AND lines Tables ",
                        " Success.. ", JOptionPane.INFORMATION_MESSAGE);

            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "Please load Header File first  ",
                    " Warning ", JOptionPane.WARNING_MESSAGE);
        }
    }


    // fill the fields with the data from the chosen row
    private void getSelectedRowValues()
    {
        int selectedRowIndex = invoiceTable.getSelectedRow();
        String headerNumber=invoiceTable.getValueAt(selectedRowIndex, 0).toString();
        numlabel.setText(headerNumber);
        dateField.setText(invoiceTable.getValueAt(selectedRowIndex, 1).toString());
        nameField.setText(invoiceTable.getValueAt(selectedRowIndex, 2).toString());
        oldNameText = nameField.getText();
        oldDateText = dateField.getText();

    }


    // Save the changes in Invoice
    private void saveChangesOfInvoice()
    {
        int i= invoiceTable.getSelectedRow();
        if(invoiceTable.getSelectedRow() != -1)
        {
            invoiceTable.setValueAt(dateField.getText(),i,1);
            invoiceTable.setValueAt(nameField.getText(),i,2);
            JOptionPane.showMessageDialog(this, "Invoice Number: "+numlabel.getText()+ "  is Updated successfully ",
                    " Save Changes ", JOptionPane.INFORMATION_MESSAGE);
        }
        else {JOptionPane.showMessageDialog(this, "No row selected ",
                " Save Changes ", JOptionPane.ERROR_MESSAGE);}
    }


    // Delete the row from the table
    public  void DeleteSelectedRow(JTable table)
    {
        if(itemFileFull==true)
        {
            if(table.getSelectedRow() != -1)
            {
                DefaultTableModel df1 = (DefaultTableModel) table.getModel();
                int rs[] = table.getSelectedRows();
                for (int i = rs.length - 1; i >= 0; i--)
                {int k = rs[i];
                    df1.removeRow(k);
                    JOptionPane.showMessageDialog(this, "Selected row is deleted Successfully",
                            " Delete Row .. ", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this, "Please Select Row of Invoice Lines ",
                        " Warning ", JOptionPane.WARNING_MESSAGE);
            }
        } else if(itemFileFull== false) {
            JOptionPane.showMessageDialog(this, " Table is Empty, Please load Invoice lines ",
                    " Delete Row ", JOptionPane.ERROR_MESSAGE);
        }
    }



    // save the table as CSV file
    public  void ExportTableToCSV (JTable tb) {

        JFileChooser ch= new JFileChooser();
        ch.setDialogTitle(" Save as");
        int userSelection= ch.showSaveDialog(this);
        if( userSelection==JFileChooser.APPROVE_OPTION)
        {
            File savedFile= ch.getSelectedFile();

            DefaultTableModel defaultTableModel = (DefaultTableModel) tb.getModel();
            int Row = defaultTableModel.getRowCount();
            int Col = defaultTableModel.getColumnCount();
            Writer writer = null;

            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(savedFile), "utf-8"));

                StringBuffer bufferHeader = new StringBuffer();
                for (int j = 0; j < Col; j++) {
                    bufferHeader.append(defaultTableModel.getColumnName(j));
                    if (j!=Col) bufferHeader.append(", ");
                }
                writer.write(bufferHeader.toString() + "\r\n");

                for (int i = 0 ; i < Row ; i++){
                    StringBuffer buffer = new StringBuffer();
                    for (int j = 0 ; j < Col ; j++){
                        buffer.append(defaultTableModel.getValueAt(i,j));
                        if (j!=Col) buffer.append(", ");
                    }
                    writer.write(buffer.toString() + "\r\n");
                }

            }
            catch (IOException e){e.printStackTrace();}
            finally
            {
                try {writer.close(); } catch (IOException e) {e.printStackTrace();}
            }
        }
    }

    // load csv files and display it
    public void loadHeaderFile()
    {
        JOptionPane.showMessageDialog(this, " please select Invoice header",
                " Invoice Header", JOptionPane.WARNING_MESSAGE);

        ArrayList<String> headerline = new ArrayList<>();

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Load File");

        String line = null;
        FileReader fr = null;
        int result = fc.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            try {
                fr = new FileReader(selectedFile);
            }
            catch (FileNotFoundException e)
            {JOptionPane.showMessageDialog(this, " File Not Found",
                    " Invoice Header File ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            BufferedReader br = new BufferedReader(fr);
            InvoiceHeader row = null;

            try {
                while ((line = br.readLine()) != null) {
                    headerline.add(line);
                    String[] headerSegments = line.split(",");

                    String invNumStr = headerSegments[0];
                    String invDateStr = headerSegments[1];
                    String invCustStr = headerSegments[2];
                    int invNum = Integer.parseInt(invNumStr);

                    row = new InvoiceHeader(invNum, invDateStr, invCustStr);
                    Invoices.add(row);

                    System.out.println(Arrays.toString(headerSegments));
                    Rs.add(headerSegments);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, " Wrong File Format",
                        " Invoice Header  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();}
            finally {
                try {br.close();
                    fr.close();
                } catch (Exception e) {e.printStackTrace();}

                Object[][] content = new Object[Rs.size()][columnNames.length];
                for(int i=0; i<Rs.size(); i++) {
                    content[i][0] = Rs.get(i)[0];
                    content[i][1] = Rs.get(i)[1];
                    content[i][2] = Rs.get(i)[2];
                }
                loadItemFile();
                invoiceTable.setModel(new DefaultTableModel(content,columnNames));
                this.pack();
            }
        }
    }

    // load the invoice lines files
    public void loadItemFile()
    {
        JOptionPane.showMessageDialog(this, "Please select Invoice Line",
                " Invoice Line", JOptionPane.WARNING_MESSAGE);
        ArrayList<String> Itemline = new ArrayList<>();
        JFileChooser fc1 = new JFileChooser();
        String line1 = null;
        FileReader fr1 = null;
        int result = fc1.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc1.getSelectedFile();
            try {
                fr1 = new FileReader(selectedFile);
            }
            catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, " File Not Found",
                        " Invoice line  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }
            BufferedReader br1 = new BufferedReader(fr1);
            InvoiceHeader row = null;

            try {
                while ((line1 = br1.readLine()) != null) {
                    Itemline.add(line1);
                    String[] headerSegments1 = line1.split(",");

                    String itemNum = headerSegments1[0];
                    String itemName=headerSegments1[1];
                    double itemPrice = Double.valueOf(headerSegments1[2]);
                    int itemCount = Integer.parseInt(headerSegments1[3]);

                    System.out.println(Arrays.toString(headerSegments1));
                    ItemsRs.add(headerSegments1);
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, " Wrong File Format",
                        " Invoice line  ", JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            } finally {
                try {br1.close();
                    fr1.close();
                } catch (Exception e) {e.printStackTrace();}

                Object[][] content1 = new Object[ItemsRs.size()][columnItemNames.length];
                for(int i=0; i<ItemsRs.size(); i++) {
                    content1[i][0] = ItemsRs.get(i)[0];
                    content1[i][1] = ItemsRs.get(i)[1];
                    content1[i][2] = ItemsRs.get(i)[2];
                    Double x= Double.valueOf(ItemsRs.get(i)[2]);
                    content1[i][3] = ItemsRs.get(i)[3];
                    int y= Integer.parseInt(ItemsRs.get(i)[3]);
                    content1[i][4]=x*y; // calculation of Total

                }
                itemTable.setModel(new DefaultTableModel(content1,columnItemNames));
                this.pack();
                itemFileFull=true;
            }
        }
    }


    public void saveFile() {
        String headers = "";
        String lines = "";
        for (InvoiceHeader invoice : Invoices) {
            String invoiceCSV = invoice.getInvoiceID() + "," + invoice.getDate() +"," + invoice.getCustomerName();
            headers += invoiceCSV;
            headers += "\n";

            for (InvoiceLine line : invoice.getItem()) {
                String lineCSV = line.getInvoiceID()+ "," + line.getItemName() +"," +line.getItemPrice()+"," + line.getItemCount();;
                lines += lineCSV;
                lines += "\n";
            }
        }
        System.out.println("Check point");
        try {
            JFileChooser jfc = new JFileChooser();
            int result = jfc.showSaveDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File headerFile = jfc.getSelectedFile();
                FileWriter hfw = new FileWriter(headerFile);
                hfw.write(headers);
                hfw.flush();
                hfw.close();
                result = jfc.showSaveDialog(this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File lineFile = jfc.getSelectedFile();
                    FileWriter lfw = new FileWriter(lineFile);
                    lfw.write(lines);
                    lfw.flush();
                    lfw.close();
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "File path is not found", "Error", JOptionPane.ERROR_MESSAGE);
        }



    }

}
