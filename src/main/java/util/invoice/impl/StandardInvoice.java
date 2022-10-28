package util.invoice.impl;

import util.invoice.AbstractInvoice;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class StandardInvoice extends AbstractInvoice {

    private Long daysOfUsage;
    private BigDecimal pricePerDay;

    public StandardInvoice(String companyName, String address, String contactDetails, Date invoiceDate,
                           Long invoiceNumber, String recipientName, String recipientAddress,
                           String recipientPhone, String recipientEmail, String productName,
                           BigDecimal totalCost, Long daysOfUsage, BigDecimal pricePerDay) {
        super(companyName, address, contactDetails, invoiceDate, invoiceNumber, recipientName, recipientAddress,
              recipientPhone, recipientEmail, productName, totalCost);
        this.daysOfUsage = daysOfUsage;
        this.pricePerDay = pricePerDay;
    }

    public Long getDaysOfUsage() {
        return daysOfUsage;
    }

    public void setDaysOfUsage(Long daysOfUsage) {
        this.daysOfUsage = daysOfUsage;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StandardInvoice that = (StandardInvoice) o;
        return Objects.equals(daysOfUsage, that.daysOfUsage) && Objects.equals(pricePerDay, that.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), daysOfUsage, pricePerDay);
    }

    @Override
    public String toString() {
        return "StandardInvoice{" + super.toString() +
                "daysOfUsage=" + daysOfUsage +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}