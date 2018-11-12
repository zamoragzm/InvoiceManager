package invoicemanager.ui;

import invoicemanager.model.Customer;
import invoicemanager.model.CustomerRegistry;
import invoicemanager.model.ServiceType;

import javax.swing.table.AbstractTableModel;

// A table model for use with JTable of customers
public class CustomerTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Customer Name", "Service Points"};
    private CustomerRegistry customerRegistry;

    // EFFECTS: constructs customer table model associated with CustomerRegistry instance
    public CustomerTableModel() {
        this.customerRegistry = CustomerRegistry.getInstance();
    }

    @Override
    public int getRowCount() {
        return customerRegistry.getNumberOfCustomers();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer customer = customerRegistry.getCustomers().get(rowIndex);

        return (columnIndex == 0 ? customer : customer.getServicePoints());
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return (columnIndex == 0 ? Customer.class : Integer.class);
    }

    // MODIFIES: this
    // EFFECTS:  adds a new customer
    public void addCustomer(Customer c) {
        customerRegistry.addCustomer(c);
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }

    // MODIFIES: this
    // EFFECTS:  adds a new service record of given type and hours to customer at given index
    public void addNewServiceRecordForCustomerAtIndex(int index, ServiceType st, int hours) {
        ((Customer) getValueAt(index, 0)).addServiceRecord(st, hours);
        fireTableRowsUpdated(index, index);
    }
}
