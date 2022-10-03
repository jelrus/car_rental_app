package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class OrderInvoice extends BaseEntity {

    private Long orderId;
    private Long invoiceId;

    public OrderInvoice() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderInvoice that = (OrderInvoice) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(invoiceId, that.invoiceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, invoiceId);
    }
}
