package invoicemanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Registry of customers
public class CustomerRegistry {
    private static CustomerRegistry instance;
    private List<Customer> customers;

    // EFFECTS: customer registry has no customers
    private CustomerRegistry() {
        customers = new ArrayList<>();
    }

    public static CustomerRegistry getInstance() {
        if (instance == null)
            instance = new CustomerRegistry();

        return instance;
    }

    // MODIFIES: this
    // EFFECTS: registry has no customers
    public void clearCustomers() {
        customers.clear();
    }

    public int getNumberOfCustomers() {
        return customers.size();
    }

    // MODIFIES: this
    // EFFECTS: adds given customer to registry
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // EFFECTS: returns unmodifiable list of customers
    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }
}
