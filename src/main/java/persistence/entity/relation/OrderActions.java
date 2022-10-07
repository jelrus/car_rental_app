package persistence.entity.relation;

import persistence.entity.BaseEntity;
import persistence.entity.annotations.Column;
import persistence.entity.annotations.MergeField;
import persistence.entity.annotations.Table;

import java.util.Objects;

@Table(tableName = "order_actions")
public class OrderActions extends BaseEntity {

    @Column(name = "order_id")
    private Long orderId;

    @MergeField(on = "action_id")
    @Column(name = "action_id")
    private Long actionId;

    public OrderActions() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        OrderActions that = (OrderActions) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, actionId);
    }

    @Override
    public String toString() {
        return "OrderActions{" + super.toString() +
                "orderId=" + orderId +
                ", actionId=" + actionId +
                '}';
    }
}