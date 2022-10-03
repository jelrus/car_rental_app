package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.Objects;

public class OrderInvoices extends BaseEntity {

    private Long orderId;
    private Collection<Long> invoiceId;

    public OrderInvoices() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Collection<Long> getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Collection<Long> invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderInvoices that = (OrderInvoices) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(invoiceId, that.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, invoiceId);
    }
}