package util.invoice.objects.impl;

import util.invoice.objects.AbstractInvoice;
import util.invoice.objects.Member;

import java.util.Objects;

public class StandardInvoice extends AbstractInvoice {

    private String fullTitle;
    private String rentalPrice;
    private String daysOfUsage;
    private String totalPrice;

    public StandardInvoice(Member sender, Member recipient) {
        super(sender, recipient);
    }

    public String getFullTitle() {
        return fullTitle;
    }

    public void setFullTitle(String fullTitle) {
        this.fullTitle = fullTitle;
    }

    public String getRentalPrice() {
        return rentalPrice;
    }

    public void setRentalPrice(String rentalPrice) {
        this.rentalPrice = rentalPrice;
    }

    public String getDaysOfUsage() {
        return daysOfUsage;
    }

    public void setDaysOfUsage(String daysOfUsage) {
        this.daysOfUsage = daysOfUsage;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StandardInvoice that = (StandardInvoice) o;
        return Objects.equals(fullTitle, that.fullTitle) && Objects.equals(rentalPrice, that.rentalPrice) &&
                Objects.equals(daysOfUsage, that.daysOfUsage) && Objects.equals(totalPrice, that.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fullTitle, rentalPrice, daysOfUsage, totalPrice);
    }

    @Override
    public String toString() {
        return "StandardInvoice{" + super.toString() +
                "fullTitle='" + fullTitle + '\'' +
                ", rentalPrice='" + rentalPrice + '\'' +
                ", daysOfUsage='" + daysOfUsage + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                '}';
    }
}