package invoicemanager.model;

// Represents a service provided for a customer
public class ServiceRecord implements Comparable<ServiceRecord> {
    public static int REG_CALLOUT = 80;
    public static int REG_SERVICE_HOURLY = 80;
    public static int REG_SERVICEPTS_BASE = 10;
    public static int REG_SERVICEPTS_HOURLY = 2;
    public static int AFTER_HOURS_CALLOUT = 120;
    public static int AFTER_HOURS_SERVICE_HOURLY = 100;
    public static int AFTER_HOURS_SERVICEPTS_BASE = 5;
    public static int AFTER_HOURS_SERVICEPTS_HOURLY = 1;
    public static int EMERG_CALLOUT = 150;
    public static int EMERG_SERVICE_HOURLY = 100;
    public static int EMERG_SERVICEPTS_BASE = 0;
    public static int EMERG_SERVICEPTS_HOURLY = 0;

    private static int nextRecordID = 0;
    private ServiceType serviceType;
    private Invoice invoice;
    private int hours;
    private int recordID;

    // EFFECTS: constructs service record for service of given number of hours and given type
    public ServiceRecord(ServiceType serviceType, int hours) {
        this.hours = hours;
        this.recordID = ++nextRecordID;
        this.serviceType = serviceType;
        buildInvoice();
    }

    public int getRecordID() {
        return recordID;
    }

    public int getHours() {
        return hours;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    // EFFECTS: returns number of service points earned with this service record
    public int getServicePoints() {
        int servicePoints = 0;

        switch(serviceType) {
            case REGULAR:
                servicePoints = REG_SERVICEPTS_BASE + hours * REG_SERVICEPTS_HOURLY;
                break;
            case AFTER_HOURS:
                servicePoints = AFTER_HOURS_SERVICEPTS_BASE + hours * AFTER_HOURS_SERVICEPTS_HOURLY;
                break;
            case EMERGENCY:
                servicePoints = EMERG_SERVICEPTS_BASE + hours * EMERG_SERVICEPTS_HOURLY;
                break;
        }

        return servicePoints;
    }

    // EFFECTS: returns callout fee in $ for this service record
    public int getCalloutFee() {
        int calloutFee = 0;

        switch(serviceType) {
            case REGULAR:
                calloutFee = REG_CALLOUT;
                break;
            case AFTER_HOURS:
                calloutFee = AFTER_HOURS_CALLOUT;
                break;
            case EMERGENCY:
                calloutFee = EMERG_CALLOUT;
                break;
        }

        return calloutFee;
    }

    // EFFECTS: returns service fee in $ for this service record
    public int getServiceFee() {
        int serviceFee = 0;

        switch(serviceType) {
            case REGULAR:
                serviceFee = REG_SERVICE_HOURLY * hours;
                break;
            case AFTER_HOURS:
                serviceFee = AFTER_HOURS_SERVICE_HOURLY * hours;
                break;
            case EMERGENCY:
                serviceFee = EMERG_SERVICE_HOURLY * hours;
                break;
        }

        return serviceFee;
    }

    @Override
    // NOTE: this class has a natural ordering that is inconsistent with equals()
    public int compareTo(ServiceRecord other) {
        return recordID - other.recordID;
    }

    // MODIFIES: this
    // EFFECTS:  create invoice for this service record
    private void buildInvoice() {
        invoice = new Invoice(recordID, getCalloutFee(), getServiceFee(), hours);
    }
}
