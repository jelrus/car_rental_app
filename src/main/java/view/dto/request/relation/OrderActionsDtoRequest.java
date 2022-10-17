package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class OrderActionsDtoRequest extends DtoRequest {

    private Long orderId;
    private Long actionId;

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
        OrderActionsDtoRequest that = (OrderActionsDtoRequest) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(actionId, that.actionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, actionId);
    }

    @Override
    public String toString() {
        return "OrderActionsDtoRequest{" +
                "orderId=" + orderId +
                ", actionId=" + actionId +
                '}';
    }
}
