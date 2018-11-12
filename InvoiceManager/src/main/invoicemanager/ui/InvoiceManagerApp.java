package invoicemanager.ui;

import invoicemanager.model.Customer;
import invoicemanager.model.CustomerRegistry;
import invoicemanager.model.Invoice;
import invoicemanager.model.ServiceType;

import javax.swing.*;
import java.awt.*;

// Invoice manager application
public class InvoiceManagerApp extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private CustomerPanel customerPanel;
    private InvoicePanel invoicePanel;

    // EFFECTS: constructs application window and loads initial data
    public InvoiceManagerApp() {
        super("Invoice Manager");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        loadData();
        addPanels();
        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        customerPanel.selectFirstCustomer();
    }

    // MODIFIES: this
    // EFFECTS:  adds customer and invoice panels to application window
    private void addPanels() {
        invoicePanel = new InvoicePanel();
        customerPanel = new CustomerPanel(invoicePanel);
        add(customerPanel);
        add(invoicePanel);
    }

    // MODIFIES: this
    // EFFECTS:  loads sample data
    private void loadData() {
        CustomerRegistry registry = CustomerRegistry.getInstance();
        Customer c1 = new Customer("C.J. Wong");
        Customer c2 = new Customer("R.T. Jones");
        Customer c3 = new Customer("A.T. Sanchez");

        registry.addCustomer(c1);
        registry.addCustomer(c2);
        registry.addCustomer(c3);

        c1.addServiceRecord(ServiceType.REGULAR, 3);
        c2.addServiceRecord(ServiceType.AFTER_HOURS, 2);
        c2.addServiceRecord(ServiceType.REGULAR, 1);
        c2.addServiceRecord(ServiceType.EMERGENCY, 1);
        c3.addServiceRecord(ServiceType.AFTER_HOURS, 4);

        Invoice i21 = c2.getServiceRecords().first().getInvoice();
        i21.makePayment(i21.getAmountOwing());

        Invoice i31 = c3.getServiceRecords().first().getInvoice();
        i31.makePayment(i31.getAmountOwing());
    }

    public static void main(String[] args) {
        new InvoiceManagerApp();
    }
}
