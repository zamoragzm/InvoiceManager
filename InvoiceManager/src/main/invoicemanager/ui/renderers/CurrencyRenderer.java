package invoicemanager.ui.renderers;

// Cell renderer for table columns showing currency in invoice table
public class CurrencyRenderer extends InvoiceRenderer {

    // EFFECTS: constructs renderer with right-aligned text
    public CurrencyRenderer() {
        super();
        setHorizontalAlignment(RIGHT);
    }

    @Override
    public void setValue(Object aValue) {
        Object result = aValue;
        if ((aValue != null)) {
            result = String.format("$%d.00", aValue);
        }
        super.setValue(result);
    }
}
