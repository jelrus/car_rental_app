package util.invoice.impl;

import util.invoice.AbstractInvoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

public class DamageRefundInvoice extends AbstractInvoice {

    private Map<String, BigDecimal> damagedPartCost;

    public DamageRefundInvoice(String companyName, String address, String contactDetails, Date invoiceDate,
                               Long invoiceNumber, String recipientName, String recipientAddress,
                               String recipientPhone, String recipientEmail, String productName,
                               BigDecimal totalCost, Map<String, BigDecimal> damagedPartCost) {
        super(companyName, address, contactDetails, invoiceDate, invoiceNumber, recipientName, recipientAddress,
              recipientPhone, recipientEmail, productName, totalCost);
        this.damagedPartCost = damagedPartCost;
    }

    public Map<String, BigDecimal> getDamagedPartCost() {
        return damagedPartCost;
    }

    public void setDamagedPartCost(Map<String, BigDecimal> damagedPartCost) {
        this.damagedPartCost = damagedPartCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DamageRefundInvoice that = (DamageRefundInvoice) o;
        return Objects.equals(damagedPartCost, that.damagedPartCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damagedPartCost);
    }

    @Override
    public String toString() {
        return "DamageRefundInvoice{" + super.toString() +
                "damagedPartCost=" + damagedPartCost +
                '}';
    }
}
