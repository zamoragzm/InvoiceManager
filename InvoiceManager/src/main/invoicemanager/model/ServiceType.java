package invoicemanager.model;

// Represents the different service types available
public enum ServiceType {
    REGULAR("Regular hours"),
    AFTER_HOURS("Evenings/Weekends"),
    EMERGENCY("Emergency callout");

    private String displayName;

    ServiceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String toString() {
        return getDisplayName();
    }
}
