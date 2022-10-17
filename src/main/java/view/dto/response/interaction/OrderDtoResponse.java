package view.dto.response.interaction;

import persistence.entity.interaction.Order;
import persistence.entity.interaction.type.OrderStatus;
import view.dto.response.DtoResponse;

import java.util.Date;
import java.util.Objects;

public class OrderDtoResponse extends DtoResponse {

    private Boolean withDriver;
    private Date leaseTermStart;
    private Date leaseTermEnd;
    private OrderStatus orderStatus;

    public OrderDtoResponse(Order order) {
        super(order.getId(), order.getCreated(), order.getUpdated(), order.getVisible());
        setWithDriver(order.getWithDriver());
        setLeaseTermStart(order.getLeaseTermStart());
        setLeaseTermEnd(order.getLeaseTermEnd());
        setOrderStatus(order.getOrderStatus());
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
        OrderDtoResponse that = (OrderDtoResponse) o;
        return Objects.equals(withDriver, that.withDriver) && Objects.equals(leaseTermStart, that.leaseTermStart) && Objects.equals(leaseTermEnd, that.leaseTermEnd) && orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(withDriver, leaseTermStart, leaseTermEnd, orderStatus);
    }

    @Override
    public String toString() {
        return "OrderDtoResponse{" +
                "withDriver=" + withDriver +
                ", leaseTermStart=" + leaseTermStart +
                ", leaseTermEnd=" + leaseTermEnd +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
