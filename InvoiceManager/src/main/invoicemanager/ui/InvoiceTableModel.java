package invoicemanager.ui;

import invoicemanager.model.Customer;
import invoicemanager.model.Invoice;
import invoicemanager.model.ServiceRecord;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

// A table model for use with JTable of invoices
public class InvoiceTableModel extends AbstractTableModel {
    final String[] columnNames = {"ID #", "Invoice Amount", "Amount Owing"};
    private Customer customer;
    private List<Invoice> invoices;

    // EFFECTS: constructs table model for invoices of given customer
    public InvoiceTableModel(Customer customer) {
        this.customer = customer;
        buildInvoiceList();
    }

    public Invoice getInvoiceAtIndex(int index) {
        return invoices.get(index);
    }

    // MODIFIES: this
    // EFFECTS:  updates display of invoice at given index
    public void invoiceAtIndexHasChanged(int index) {
        fireTableRowsUpdated(index, index);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return invoices.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Invoice selected = invoices.get(rowIndex);

        switch(columnIndex) {
            case 0:
                return selected.getRecordID();
            case 1:
                return selected.getInvoiceAmount();
            case 2:
                return selected.getAmountOwing();
            default:
                return null;  // shouldn't get here
        }
    }

    // MODIFIES: this
    // EFFECTS: rebuilds list of invoices for customer
    public void dataHasChanged() {
        buildInvoiceList();
        fireTableDataChanged();
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    // MODIFIES: this
    // EFFECTS:  builds list of invoices for customer
    private void buildInvoiceList() {
        invoices = new ArrayList<Invoice>();

        if (customer != null) {
            for (ServiceRecord next : customer.getServiceRecords()) {
                invoices.add(next.getInvoice());
            }
        }
    }
}
