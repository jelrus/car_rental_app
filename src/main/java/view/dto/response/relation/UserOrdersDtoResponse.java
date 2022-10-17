package view.dto.response.relation;

import persistence.entity.relation.UserOrders;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class UserOrdersDtoResponse extends DtoResponse {

    private Long userId;
    private Long orderId;

    public UserOrdersDtoResponse(UserOrders uOrds) {
        super(uOrds.getId(), uOrds.getCreated(), uOrds.getUpdated(), uOrds.getVisible());
        setUserId(uOrds.getUserId());
        setOrderId(uOrds.getOrderId());
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
        UserOrdersDtoResponse that = (UserOrdersDtoResponse) o;
        return Objects.equals(userId, that.userId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, orderId);
    }
}
