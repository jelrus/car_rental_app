package view.dto.request.interaction;

import persistence.entity.interaction.type.OrderStatus;

import java.util.Objects;

public class StatusDtoRequest {

    private OrderStatus orderStatus;

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
        StatusDtoRequest that = (StatusDtoRequest) o;
        return orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderStatus);
    }

    @Override
    public String toString() {
        return "StatusDtoRequest{" +
                "orderStatus=" + orderStatus +
                '}';
    }
}