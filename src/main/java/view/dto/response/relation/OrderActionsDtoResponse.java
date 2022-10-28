package view.dto.response.relation;

import persistence.entity.relation.OrderActions;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class OrderActionsDtoResponse extends DtoResponse {

    private Long orderId;
    private Long actionId;

    public OrderActionsDtoResponse(OrderActions orderActions) {
        super(orderActions.getId(), orderActions.getCreated(), orderActions.getUpdated(), orderActions.getVisible());
        setOrderId(orderActions.getOrderId());
        setActionId(orderActions.getActionId());
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
        OrderActionsDtoResponse that = (OrderActionsDtoResponse) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, actionId);
    }

    @Override
    public String toString() {
        return "OrderActionsDtoResponse{" + super.toString() +
                "orderId=" + orderId +
                ", actionId=" + actionId +
                '}';
    }
}