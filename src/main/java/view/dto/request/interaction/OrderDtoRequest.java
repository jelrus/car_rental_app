package view.dto.request.interaction;

import persistence.entity.interaction.type.OrderStatus;
import view.dto.request.DtoRequest;

import java.util.Date;
import java.util.Objects;

public class OrderDtoRequest extends DtoRequest {

    private Boolean withDriver;
    private Date start;
    private Date end;
    private OrderStatus orderStatus;
    private Boolean enabled;

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
        OrderDtoRequest that = (OrderDtoRequest) o;
        return Objects.equals(withDriver, that.withDriver) &&
               Objects.equals(start, that.start) &&
               Objects.equals(end, that.end) &&
               orderStatus == that.orderStatus &&
               Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(withDriver, start, end, orderStatus, enabled);
    }

    @Override
    public String toString() {
        return "OrderDtoRequest{" +
                "withDriver=" + withDriver +
                ", start=" + start +
                ", end=" + end +
                ", orderStatus=" + orderStatus +
                ", enabled=" + enabled +
                '}';
    }
}