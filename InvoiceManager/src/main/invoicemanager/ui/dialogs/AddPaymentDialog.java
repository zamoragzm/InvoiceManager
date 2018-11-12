package invoicemanager.ui.dialogs;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog for entering new customer information
public class AddPaymentDialog extends JDialog implements PropertyChangeListener {
    private static final String CANCEL = "Cancel";
    private static final String ADD = "Add Payment";
    private JTextField paymentField;
    private JOptionPane optionPane;
    private Integer paymentAmount;

    // EFFECTS: constructs modal dialog with given window as parent
    public AddPaymentDialog(Window parent) {
        super(parent, "Add payment", ModalityType.APPLICATION_MODAL);

        String prompt = "Enter payment amount $:";
        paymentField = new JTextField();

        Object[] elements = {prompt, paymentField};
        Object[] options = {CANCEL, ADD};

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

    public Integer getPaymentAmount() {
        return paymentAmount;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String prop = evt.getPropertyName();

        if (JOptionPane.VALUE_PROPERTY.equals(prop) ||
                JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }

            // reset JOptionPane value so that, if necessary, user can select same button again
            // and propertyChange still fires (may happen when user's input is not valid)
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            if (ADD.equals(value)) {
                if (parsePayment(paymentField.getText()))
                    dispose();
                else {
                    paymentField.setText(null);
                    JOptionPane.showMessageDialog(this, "Input must be an integer...", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }

            } else {
                paymentAmount = null;
                dispose();
            }
        }
    }

    // EFFECTS: return true if given string can be parsed as a payment amount (an integer); false otherwise
    private boolean parsePayment(String paymentString) {
        try {
            paymentAmount = Integer.parseInt(paymentString);
            return true;
        } catch (NumberFormatException e) {
            paymentAmount = null;
            return false;
        }
    }
}
