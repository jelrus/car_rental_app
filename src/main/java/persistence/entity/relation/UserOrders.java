package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class UserOrders extends BaseEntity {

    private Long userId;
    private Long orderId;

    public UserOrders() {
        super();
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
        UserOrders that = (UserOrders) o;
        return Objects.equals(userId, that.userId) &&
               Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, orderId);
    }

    @Override
    public String toString() {
        return "UserOrders{" + super.toString() +
                "userId=" + userId +
                ", orderId=" + orderId +
                '}';
    }
}