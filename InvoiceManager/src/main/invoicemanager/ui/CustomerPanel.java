package invoicemanager.ui;

import invoicemanager.model.Customer;
import invoicemanager.ui.dialogs.AddServiceRecordDialog;
import invoicemanager.ui.dialogs.NewCustomerDialog;
import invoicemanager.ui.renderers.CustomerRenderer;
import invoicemanager.ui.renderers.ServicePointsRenderer;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// The panel in which customer information is displayed
public class CustomerPanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTable customerTable;
    private CustomerTableModel customerTableModel;
    private InvoicePanel invoicePanel;

    // EFFECTS: constructs customer panel associated with given invoice panel
    public CustomerPanel(InvoicePanel invoicePanel) {
        super();
        this.invoicePanel = invoicePanel;
        customerTable = new JTable(0, 2);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addLabel();
        buildCustomerTable();
        addCustomerTable();
        addButtons();
    }

    // REQUIRES: customer table has at least one customer
    // MODIFIES: this
    // EFFECTS:  select the first customer in the table
    public void selectFirstCustomer() {
        customerTable.setRowSelectionInterval(0, 0);
    }

    // MODIFIES: this
    // EFFECTS:  add label for this panel
    private void addLabel() {
        Box labelHolder = Box.createHorizontalBox();
        labelHolder.setAlignmentX(LEFT_ALIGNMENT);
        labelHolder.setBorder(BorderFactory.createEtchedBorder());
        JLabel customerLbl = new JLabel("Customers");
        customerLbl.setFont(new Font("Arial", Font.BOLD, 14));
        customerLbl.setAlignmentX(LEFT_ALIGNMENT);
        labelHolder.add(Box.createHorizontalStrut(5));
        labelHolder.add(customerLbl);
        labelHolder.add(Box.createHorizontalGlue());
        add(labelHolder);
    }

    // MODIFIES: this
    // EFFECTS:  builds the table of customers
    private void buildCustomerTable() {
        customerTableModel = new CustomerTableModel();
        customerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        customerTable.setModel(customerTableModel);
        customerTable.getColumnModel().getColumn(0).setCellRenderer(new CustomerRenderer());
        customerTable.getColumnModel().getColumn(1).setCellRenderer(new ServicePointsRenderer());
        customerTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = customerTable.getSelectedRow();
                    Customer selectedCustomer = (Customer) customerTableModel.getValueAt(selectedRow, 0);
                    invoicePanel.customerSelected(selectedCustomer);
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS:  adds table of customers to this panel
    private void addCustomerTable() {
        JScrollPane customerScrollPane = new JScrollPane(customerTable);
        customerScrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        customerScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        customerScrollPane.setBorder(BorderFactory.createEtchedBorder());
        add(customerScrollPane);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons
    private void addButtons() {
        Box buttonHolder = Box.createHorizontalBox();
        buttonHolder.setBorder(BorderFactory.createEtchedBorder());
        buttonHolder.add(Box.createHorizontalGlue());
        buttonHolder.add(buildNewCustomerBtn());
        buttonHolder.add(buildAddServiceBtn());
        buttonHolder.add(Box.createHorizontalGlue());
        buttonHolder.setAlignmentX(LEFT_ALIGNMENT);
        add(buttonHolder);
    }

    // EFFECTS: creates "new customer" button and returns it
    private JButton buildNewCustomerBtn() {
        JButton newCustomerBtn = new JButton("New Customer");

        newCustomerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.windowForComponent(CustomerPanel.this);
                NewCustomerDialog customerDialog = new NewCustomerDialog(parentWindow);
                customerDialog.setLocationRelativeTo(CustomerPanel.this);
                customerDialog.setVisible(true);
                String customerName = customerDialog.getCustomerName();

                if (customerName != null) {
                    Customer c = new Customer(customerName);
                    customerTableModel.addCustomer(c);
                    int lastIndex = customerTableModel.getRowCount() - 1;
                    customerTable.setRowSelectionInterval(lastIndex, lastIndex);
                }
            }
        });

        return newCustomerBtn;
    }

    // EFFECTS: creates "add service record" button and returns it
    private JButton buildAddServiceBtn() {
        JButton addServiceBtn = new JButton("Add Service Record");

        addServiceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.windowForComponent(CustomerPanel.this);
                AddServiceRecordDialog addServiceRecordDialog = new AddServiceRecordDialog(parentWindow);
                addServiceRecordDialog.setLocationRelativeTo(CustomerPanel.this);
                addServiceRecordDialog.setVisible(true);

                if (addServiceRecordDialog.getHoursWorked() != null) {
                    customerTableModel.addNewServiceRecordForCustomerAtIndex(customerTable.getSelectedRow(),
                            addServiceRecordDialog.getServiceType(), addServiceRecordDialog.getHoursWorked());
                    invoicePanel.selectedCustomerUpdated();
                }
            }
        });

        return addServiceBtn;
    }
}
