package util.invoice.objects.impl;

import util.invoice.objects.AbstractInvoice;
import util.invoice.objects.Member;

import java.util.Map;
import java.util.Objects;

public class DamageRefundInvoice extends AbstractInvoice {

    private Map<String, String> damageCost;
    private String totalCost;

    public DamageRefundInvoice(Member sender, Member recipient) {
        super(sender, recipient);
    }

    public Map<String, String> getDamageCost() {
        return damageCost;
    }

    public void setDamageCost(Map<String, String> damageCost) {
        this.damageCost = damageCost;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DamageRefundInvoice that = (DamageRefundInvoice) o;
        return Objects.equals(damageCost, that.damageCost) && Objects.equals(totalCost, that.totalCost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), damageCost, totalCost);
    }

    @Override
    public String toString() {
        return "DamageRefundInvoice{" + super.toString() +
                "damageCost=" + damageCost +
                ", totalCost='" + totalCost + '\'' +
                '}';
    }
}