package view.dto.response.relation;

import persistence.entity.relation.OrderActions;
import view.dto.response.DtoResponse;

import java.util.Date;
import java.util.Objects;

public class OrderActionsDtoResponse extends DtoResponse {

    private Long orderId;
    private Long actionId;

    public OrderActionsDtoResponse(OrderActions oActs) {
        super(oActs.getId(), oActs.getCreated(), oActs.getUpdated(), oActs.getVisible());
        setOrderId(oActs.getOrderId());
        setActionId(oActs.getActionId());
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
        OrderActionsDtoResponse that = (OrderActionsDtoResponse) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, actionId);
    }

    @Override
    public String toString() {
        return "OrderActionsDtoResponse{" + super.toString() +
                "orderId=" + orderId +
                ", actionId=" + actionId +
                '}';
    }
}
