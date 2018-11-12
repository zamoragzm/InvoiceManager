package invoicemanager.ui.dialogs;

import invoicemanager.model.ServiceType;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

// A dialog for entering new customer information
public class AddServiceRecordDialog extends JDialog implements PropertyChangeListener {
    private static final String CANCEL = "Cancel";
    private static final String ADD = "Add Record";
    private JTextField hoursWorkedField;
    private JComboBox<ServiceType> serviceTypeComboBox;
    private JOptionPane optionPane;
    private Integer hoursWorked;
    private ServiceType serviceType;

    // EFFECTS: constructs modal dialog with given window as parent
    public AddServiceRecordDialog(Window parent) {
        super(parent, "Add Service Record", ModalityType.APPLICATION_MODAL);

        String hoursPrompt = "Enter hours worked:";
        hoursWorkedField = new JTextField();
        String serviceTypePrompt = "Select service type:";
        serviceTypeComboBox = new JComboBox<ServiceType>(ServiceType.values());

        Object[] elements = {hoursPrompt, hoursWorkedField, serviceTypePrompt, serviceTypeComboBox};
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

    public Integer getHoursWorked() {
        return hoursWorked;
    }

    public ServiceType getServiceType() {
        return serviceType;
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
                if (parseHoursWorked(hoursWorkedField.getText())) {
                    serviceType = (ServiceType) serviceTypeComboBox.getSelectedItem();
                    dispose();
                }
                else {
                    hoursWorkedField.setText(null);
                    JOptionPane.showMessageDialog(this, "Input must be an integer...", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                hoursWorked = null;
                serviceType = null;
                dispose();
            }
        }
    }

    // EFFECTS: return true if given string can be parsed as a payment amount (an integer); false otherwise
    private boolean parseHoursWorked(String paymentString) {
        try {
            hoursWorked = Integer.parseInt(paymentString);
            return true;
        } catch (NumberFormatException e) {
            hoursWorked = null;
            return false;
        }
    }
}
