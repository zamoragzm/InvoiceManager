package invoicemanager.tests;

import invoicemanager.model.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for the Invoice class
public class InvoiceTest {
    private Invoice testInvoice;

    @BeforeEach
    public void runBefore() {
        testInvoice = new Invoice(1, 10, 4, 1);
    }

    @Test
    public void testConstructor() {
        assertEquals(14, testInvoice.getAmountOwing());
        assertFalse(testInvoice.isPaidInFull());
    }

    @Test
    public void testMakePayment() {
        testInvoice.makePayment(4);

        assertEquals(10, testInvoice.getAmountOwing());
        assertFalse(testInvoice.isPaidInFull());
    }

    @Test
    public void testPayInFull() {
        testInvoice.makePayment(testInvoice.getAmountOwing());
        assertTrue(testInvoice.isPaidInFull());
    }

}