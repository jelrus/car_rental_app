package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class UserOrdersDtoRequest extends DtoRequest {

    private Long userId;
    private Long orderId;

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
        UserOrdersDtoRequest that = (UserOrdersDtoRequest) o;
        return Objects.equals(userId, that.userId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderId);
    }

    @Override
    public String toString() {
        return "UserOrdersDtoRequest{" +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}
