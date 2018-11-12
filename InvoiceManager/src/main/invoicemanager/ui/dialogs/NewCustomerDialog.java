package invoicemanager.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog for entering new customer information
public class NewCustomerDialog extends JDialog implements PropertyChangeListener {
    private static final String CANCEL = "Cancel";
    private static final String CREATE = "Create";
    private JTextField customerNameField;
    private JOptionPane optionPane;
    private String customerName;

    // EFFECTS: constructs modal dialog with given window as parent
    public NewCustomerDialog(Window parent) {
        super(parent, "New Customer", ModalityType.APPLICATION_MODAL);

        String prompt = "Enter customer name:";
        customerNameField = new JTextField();

        Object[] elements = {prompt, customerNameField};
        Object[] options = {CANCEL, CREATE};

        optionPane = new JOptionPane(elements,
                JOptionPane.QUESTION_MESSAGE,
                JOptionPane.YES_NO_OPTION,
                null,
                options,
                options[0]);

        setContentPane(optionPane);
        optionPane.addPropertyChangeListener(this);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        pack();
    }

    public String getCustomerName() {
        return customerName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();

        if (JOptionPane.VALUE_PROPERTY.equals(prop) ||
                JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)) {
            Object value = optionPane.getValue();

            if (CREATE.equals(value)) {
                customerName = customerNameField.getText();
            } else {
                customerName = null;
            }
            dispose();
        }
    }
}
