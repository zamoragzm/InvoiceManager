package invoicemanager.tests;

import invoicemanager.model.Customer;
import invoicemanager.model.CustomerRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// Unit tests for customer registry
public class CustomerRegistryTest {
    private CustomerRegistry testCustomerRegistry;

    @BeforeEach
    public void runBefore() {
        testCustomerRegistry = CustomerRegistry.getInstance();
        testCustomerRegistry.clearCustomers();
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testCustomerRegistry.getNumberOfCustomers());
        assertTrue(testCustomerRegistry.getCustomers().isEmpty());
    }

    @Test
    public void testAddCustomers() {
        Customer c1 = new Customer("Smith");
        Customer c2 = new Customer("Wang");

        testCustomerRegistry.addCustomer(c1);
        testCustomerRegistry.addCustomer(c2);

        List<Customer> customers = testCustomerRegistry.getCustomers();
        assertEquals(c1, customers.get(0));
        assertEquals(c2, customers.get(1));
        assertEquals(2, customers.size());
        assertEquals(2, testCustomerRegistry.getNumberOfCustomers());
    }
}