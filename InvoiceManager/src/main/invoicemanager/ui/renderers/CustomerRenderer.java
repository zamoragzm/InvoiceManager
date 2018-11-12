package invoicemanager.ui.renderers;

import invoicemanager.model.Customer;

import javax.swing.*;
import java.awt.*;

// Renderer for cells displaying customer fields
public class CustomerRenderer extends BorderCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus,
                row, column);

        Customer customer = (Customer) table.getValueAt(row, 0);
        setForeground(customer.isInGoodStanding() ? FOREGROUND_DEFAULT : FOREGROUND_HIGHLIGHT);

        return this;
    }
}
