package persistence.entity.interaction;

import persistence.entity.BaseEntity;
import persistence.entity.interaction.type.OrderStatus;

import java.util.Date;
import java.util.Objects;

public class Order extends BaseEntity {

    private Boolean withDriver;
    private Date start;
    private Date end;
    private OrderStatus orderStatus;
    private Boolean enabled;

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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Order order = (Order) o;
        return Objects.equals(withDriver, order.withDriver) &&
               Objects.equals(start, order.start) &&
               Objects.equals(end, order.end) &&
               orderStatus == order.orderStatus &&
               Objects.equals(enabled, order.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), withDriver, start, end, orderStatus, enabled);
    }

    @Override
    public String toString() {
        return "Order{" + super.toString() +
                "withDriver=" + withDriver +
                ", start=" + start +
                ", end=" + end +
                ", orderStatus=" + orderStatus +
                ", enabled=" + enabled +
                '}';
    }
}