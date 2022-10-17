package view.dto.response.relation;

import persistence.entity.relation.OrderPassport;
import view.dto.response.DtoResponse;

import java.util.Date;
import java.util.Objects;

public class OrderPassportDtoResponse extends DtoResponse {

    private Long orderId;
    private Long passportId;

    public OrderPassportDtoResponse(OrderPassport oPass) {
        super(oPass.getId(), oPass.getCreated(), oPass.getUpdated(), oPass.getVisible());
        setOrderId(oPass.getOrderId());
        setPassportId(oPass.getPassportId());
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getPassportId() {
        return passportId;
    }

    public void setPassportId(Long passportId) {
        this.passportId = passportId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPassportDtoResponse that = (OrderPassportDtoResponse) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(passportId, that.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, passportId);
    }

    @Override
    public String toString() {
        return "OrderPassportDtoResponse{" + super.toString() +
                "orderId=" + orderId +
                ", passportId=" + passportId +
                '}';
    }
}
