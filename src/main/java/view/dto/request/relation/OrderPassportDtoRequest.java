package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class OrderPassportDtoRequest extends DtoRequest {

    private Long orderId;
    private Long passportId;

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
        OrderPassportDtoRequest that = (OrderPassportDtoRequest) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(passportId, that.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, passportId);
    }

    @Override
    public String toString() {
        return "OrderPassportDtoRequest{" +
                "orderId=" + orderId +
                ", passportId=" + passportId +
                '}';
    }
}
