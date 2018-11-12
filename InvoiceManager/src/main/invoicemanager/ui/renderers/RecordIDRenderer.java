package invoicemanager.ui.renderers;

// Cell renderer for record/invoice IDs
public class RecordIDRenderer extends InvoiceRenderer {

    @Override
    public void setValue(Object aValue) {
        Object result = aValue;
        if ((aValue != null)) {
            result = String.format("%04d", aValue);
        }
        super.setValue(result);
    }
}
