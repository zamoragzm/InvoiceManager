package invoicemanager.model;

import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

// Represents a customer
public class Customer {
    private String name;
    private SortedSet<ServiceRecord> serviceRecords;
    private int servicePoints;

    // EFFECTS: constructs customer with given name, no service records and no service points
    public Customer(String name) {
        this.name = name;
        serviceRecords = new TreeSet<>();
        servicePoints = 0;
    }

    public String getName() {
        return name;
    }

    // MODIFIES: this
    // EFFECTS:  adds service record of given type and given number of hours to this customer;
    //           updates customer's service points
    public void addServiceRecord(ServiceType serviceType, int hours) {
        ServiceRecord serviceRecord = new ServiceRecord(serviceType, hours);
        serviceRecords.add(serviceRecord);
        servicePoints += serviceRecord.getServicePoints();
    }

    // EFFECTS: returns an unmodifiable set of service records for this customer, ordered by record ID
    public SortedSet<ServiceRecord> getServiceRecords() {
        return Collections.unmodifiableSortedSet(serviceRecords);
    }

    public int getServicePoints() {
        return servicePoints;
    }

    // EFFECTS: returns true if customer has paid invoice for all service records in full; false otherwise
    public boolean isInGoodStanding() {
        for (ServiceRecord next : serviceRecords) {
            if (!next.getInvoice().isPaidInFull())
                return false;
        }

        return true;
    }

    public String toString() {
        return name;
    }
}


