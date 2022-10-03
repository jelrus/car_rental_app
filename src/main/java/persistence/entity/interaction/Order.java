package persistence.entity.interaction;

import persistence.entity.BaseEntity;
import persistence.entity.interaction.type.OrderStatus;

import java.util.Date;
import java.util.Objects;

public class Order extends BaseEntity {

    private Boolean withDriver;
    private Date leaseTermStart;
    private Date leaseTermEnd;
    private OrderStatus orderStatus;

    public Order() {
        super();
        this.orderStatus = OrderStatus.PROCESSING;
    }

    public Boolean getWithDriver() {
        return withDriver;
    }

    public void setWithDriver(Boolean withDriver) {
        this.withDriver = withDriver;
    }

    public Date getLeaseTermStart() {
        return leaseTermStart;
    }

    public void setLeaseTermStart(Date leaseTermStart) {
        this.leaseTermStart = leaseTermStart;
    }

    public Date getLeaseTermEnd() {
        return leaseTermEnd;
    }

    public void setLeaseTermEnd(Date leaseTermEnd) {
        this.leaseTermEnd = leaseTermEnd;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(withDriver, order.withDriver) &&
               Objects.equals(leaseTermStart, order.leaseTermStart) &&
               Objects.equals(leaseTermEnd, order.leaseTermEnd) &&
               orderStatus == order.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), withDriver, leaseTermStart, leaseTermEnd, orderStatus);
    }
}