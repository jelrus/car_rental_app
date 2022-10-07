package persistence.entity.relation;

import persistence.entity.BaseEntity;
import persistence.entity.annotations.Column;
import persistence.entity.annotations.MergeField;
import persistence.entity.annotations.Table;

import java.util.Objects;

@Table(tableName = "user_orders")
public class UserOrders extends BaseEntity {

    @Column(name = "user_id")
    private Long userId;

    @MergeField(on = "order_id")
    @Column(name = "order_id")
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
        if (!super.equals(o)) return false;
        UserOrders that = (UserOrders) o;
        return Objects.equals(userId, that.userId) && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), userId, orderId);
    }
}