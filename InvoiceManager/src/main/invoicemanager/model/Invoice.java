package invoicemanager.model;

// Represents a customer invoice for service
public class Invoice {
    private int calloutFee;
    private int serviceFee;
    private int hours;
    private int invoiceAmount;
    private int totalPaid;
    private int recordID;

    // EFFECTS: constructs invoice with given id, callout fee, service fee and hours worked
    public Invoice(int recordID, int calloutFee, int serviceFee, int hours) {
        this.recordID = recordID;
        this.calloutFee = calloutFee;
        this.serviceFee = serviceFee;
        this.hours = hours;
        invoiceAmount = calloutFee + serviceFee;
        totalPaid = 0;
    }

    public int getRecordID() {
        return recordID;
    }

    public int getCalloutFee() {
        return calloutFee;
    }

    public int getServiceFee() {
        return serviceFee;
    }

    public int getHours() {
        return hours;
    }

    public int getInvoiceAmount() {
        return invoiceAmount;
    }

    // EFFECTS: returns amount still owing on this invoice
    public int getAmountOwing() {
        return invoiceAmount - totalPaid;
    }

    // MODIFIES: this
    // EFFECTS:  makes a payment on this invoice of the given amount
    public void makePayment(int payment) {
        totalPaid += payment;
    }

    // EFFECTS: returns true if invoice has been paid in full
    public boolean isPaidInFull() {
        return getAmountOwing() <= 0;
    }

    public String toString() {
        return new Integer(getRecordID()).toString();
    }
}
