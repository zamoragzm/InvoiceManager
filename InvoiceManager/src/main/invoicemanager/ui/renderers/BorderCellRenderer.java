package invoicemanager.ui.renderers;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

// Cell renderer that creates small border at left and right edges
public class BorderCellRenderer extends DefaultTableCellRenderer {
    final static Color FOREGROUND_HIGHLIGHT = new Color(255, 77, 87);
    final static Color FOREGROUND_DEFAULT = new Color(48, 47, 47);

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        final Color SELECTED_COLOR = new Color(200, 200, 200);
        final Color UNSELECTED_COLOR = new Color(225, 225, 225);

        final int BORDER_SIZE = 5;

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        setBorder(BorderFactory.createEmptyBorder(0, BORDER_SIZE, 0, BORDER_SIZE));
        setForeground(FOREGROUND_DEFAULT);
        setBackground(isSelected ? SELECTED_COLOR : UNSELECTED_COLOR);
        return this;
    }
}
