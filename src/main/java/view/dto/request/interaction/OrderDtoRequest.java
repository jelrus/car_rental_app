package view.dto.request.interaction;

import persistence.entity.interaction.type.OrderStatus;
import view.dto.request.DtoRequest;

import java.util.Date;
import java.util.Objects;

public class OrderDtoRequest extends DtoRequest {

    private boolean withDriver;
    private Date leaseTermStart;
    private Date leaseTermEnd;
    private OrderStatus orderStatus;

    public boolean isWithDriver() {
        return withDriver;
    }

    public void setWithDriver(boolean withDriver) {
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
        OrderDtoRequest that = (OrderDtoRequest) o;
        return withDriver == that.withDriver &&
               Objects.equals(leaseTermStart, that.leaseTermStart) &&
               Objects.equals(leaseTermEnd, that.leaseTermEnd) &&
               orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(withDriver, leaseTermStart, leaseTermEnd, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderDtoRequest{" +
                "withDriver=" + withDriver +
                ", leaseTermStart=" + leaseTermStart +
                ", leaseTermEnd=" + leaseTermEnd +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
