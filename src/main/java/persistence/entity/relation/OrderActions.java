package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Collection;
import java.util.Objects;

public class OrderActions extends BaseEntity {

    private Long orderId;
    private Collection<Long> actionId;

    public OrderActions() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Collection<Long> getActionId() {
        return actionId;
    }

    public void setActionId(Collection<Long> actionId) {
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
}