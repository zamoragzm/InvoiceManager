package invoicemanager.tests;

import invoicemanager.model.Invoice;
import invoicemanager.model.ServiceRecord;
import invoicemanager.model.ServiceType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


// Unit tests for ServiceRecord
public class ServiceRecordTest {
    private ServiceRecord testServiceRecord;

    @Test
    public void testRegular() {
        testServiceRecord = new ServiceRecord(ServiceType.REGULAR, 2);

        assertEquals(ServiceRecord.REG_SERVICEPTS_BASE + 2 * ServiceRecord.REG_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = ServiceRecord.REG_CALLOUT;
        int serviceFee = ServiceRecord.REG_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testAfterHours() {
        testServiceRecord = new ServiceRecord(ServiceType.AFTER_HOURS, 2);

        assertEquals(ServiceRecord.AFTER_HOURS_SERVICEPTS_BASE + 2 * ServiceRecord.AFTER_HOURS_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = ServiceRecord.AFTER_HOURS_CALLOUT;
        int serviceFee = ServiceRecord.AFTER_HOURS_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }

    @Test
    public void testEmergency() {
        testServiceRecord = new ServiceRecord(ServiceType.EMERGENCY, 2);

        assertEquals(ServiceRecord.EMERG_SERVICEPTS_BASE + 2 * ServiceRecord.EMERG_SERVICEPTS_HOURLY,
                testServiceRecord.getServicePoints());

        int calloutFee = ServiceRecord.EMERG_CALLOUT;
        int serviceFee = ServiceRecord.EMERG_SERVICE_HOURLY * 2;
        assertEquals(calloutFee, testServiceRecord.getCalloutFee());
        assertEquals(serviceFee, testServiceRecord.getServiceFee());

        Invoice invoice = testServiceRecord.getInvoice();
        assertEquals(calloutFee + serviceFee, invoice.getAmountOwing());
    }
}