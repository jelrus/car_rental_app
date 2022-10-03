package persistence.entity.relation;

import persistence.entity.BaseEntity;
import persistence.entity.interaction.Order;

import java.util.Collection;
import java.util.Objects;

public class UserOrders extends BaseEntity {

    private Long userId;
    private Collection<Order> ordersId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Collection<Order> getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(Collection<Order> ordersId) {
        this.ordersId = ordersId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserOrders that = (UserOrders) o;
        return Objects.equals(userId, that.userId) && Objects.equals(ordersId, that.ordersId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, ordersId);
    }
}