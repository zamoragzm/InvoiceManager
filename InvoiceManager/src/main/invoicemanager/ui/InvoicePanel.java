package invoicemanager.ui;

import invoicemanager.model.Customer;
import invoicemanager.model.Invoice;
import invoicemanager.ui.dialogs.AddPaymentDialog;
import invoicemanager.ui.renderers.CurrencyRenderer;
import invoicemanager.ui.renderers.RecordIDRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Panel in which invoices are displayed
public class InvoicePanel extends JPanel {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;
    private final JTable invoiceTable;
    private InvoiceTableModel invoiceTableModel;

    // EFFECTS: constructs invoice panel
    public InvoicePanel() {
        super();
        invoiceTable = new JTable(0, 3);
        invoiceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        addLabel();
        addInvoiceTable();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS:  updates panel with invoices for selected customer
    public void customerSelected(Customer customer) {
        invoiceTableModel = new InvoiceTableModel(customer);
        invoiceTable.setModel(invoiceTableModel);
        if (invoiceTableModel.getRowCount() > 0)
            invoiceTable.addRowSelectionInterval(0, 0);

        RecordIDRenderer idRenderer = new RecordIDRenderer();
        CurrencyRenderer currencyRenderer = new CurrencyRenderer();
        invoiceTable.getColumnModel().getColumn(0).setCellRenderer(idRenderer);
        invoiceTable.getColumnModel().getColumn(1).setCellRenderer(currencyRenderer);
        invoiceTable.getColumnModel().getColumn(2).setCellRenderer(currencyRenderer);
    }

    // MODIFIES: this
    // EFFECTS:  updates panel with invoices for current customer
    public void selectedCustomerUpdated() {
        invoiceTableModel.dataHasChanged();
        invoiceTable.addRowSelectionInterval(invoiceTable.getRowCount() - 1, invoiceTable.getRowCount() - 1);
    }

    // MODIFIES: this
    // EFFECTS: adds label for this panel
    private void addLabel() {
        Box labelHolder = Box.createHorizontalBox();
        labelHolder.setAlignmentX(LEFT_ALIGNMENT);
        labelHolder.setBorder(BorderFactory.createEtchedBorder());
        JLabel invoiceLbl = new JLabel("Invoices");
        invoiceLbl.setFont(new Font("Arial", Font.BOLD, 14));
        invoiceLbl.setAlignmentX(LEFT_ALIGNMENT);
        labelHolder.add(Box.createHorizontalStrut(5));
        labelHolder.add(invoiceLbl);
        labelHolder.add(Box.createHorizontalGlue());
        add(labelHolder);
    }

    // MODIFIES: this
    // EFFECTS:  adds table of invoices to this panel
    private void addInvoiceTable() {
        JScrollPane customerScrollPane = new JScrollPane(invoiceTable);
        customerScrollPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        customerScrollPane.setAlignmentX(LEFT_ALIGNMENT);
        customerScrollPane.setBorder(BorderFactory.createEtchedBorder());
        add(customerScrollPane);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to this panel
    private void addButtons() {
        Box buttonHolder = Box.createHorizontalBox();
        buttonHolder.setAlignmentX(LEFT_ALIGNMENT);
        buttonHolder.setBorder(BorderFactory.createEtchedBorder());
        buttonHolder.add(Box.createHorizontalGlue());
        buttonHolder.add(buildMakePaymentBtn());
        buttonHolder.add(Box.createHorizontalGlue());

        add(buttonHolder);
    }

    // EFFECTS: creates "make payment" button and returns it
    private JButton buildMakePaymentBtn() {
        JButton newPaymentBtn = new JButton("Make Payment");

        newPaymentBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Window parentWindow = SwingUtilities.windowForComponent(InvoicePanel.this);
                AddPaymentDialog addPaymentDialog = new AddPaymentDialog(parentWindow);
                addPaymentDialog.setLocationRelativeTo(InvoicePanel.this);
                addPaymentDialog.setVisible(true);

                Integer paymentAmount = addPaymentDialog.getPaymentAmount();
                if (paymentAmount != null) {
                    int selectedRow = invoiceTable.getSelectedRow();
                    Invoice selected = invoiceTableModel.getInvoiceAtIndex(selectedRow);
                    selected.makePayment(paymentAmount);
                    invoiceTableModel.invoiceAtIndexHasChanged(invoiceTable.getSelectedRow());
                }
            }
        });

        return newPaymentBtn;
    }
}
