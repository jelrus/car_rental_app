package view.dto.response.relation;

import persistence.entity.relation.UserOrders;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class UserOrdersDtoResponse extends DtoResponse {

    private Long userId;
    private Long orderId;

    public UserOrdersDtoResponse(UserOrders userOrders) {
        super(userOrders.getId(), userOrders.getCreated(), userOrders.getUpdated(), userOrders.getVisible());
        setUserId(userOrders.getUserId());
        setOrderId(userOrders.getOrderId());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserOrdersDtoResponse that = (UserOrdersDtoResponse) o;
        return Objects.equals(userId, that.userId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, orderId);
    }

    @Override
    public String toString() {
        return "UserOrdersDtoResponse{" + super.toString() +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}