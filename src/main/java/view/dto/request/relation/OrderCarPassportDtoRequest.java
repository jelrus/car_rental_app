package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class OrderCarPassportDtoRequest extends DtoRequest {

    private Long orderId;
    private Long carId;
    private Long passportId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
        OrderCarPassportDtoRequest that = (OrderCarPassportDtoRequest) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(carId, that.carId) &&
               Objects.equals(passportId, that.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, carId, passportId);
    }

    @Override
    public String toString() {
        return "OrderCarPassportDtoRequest{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", passportId=" + passportId +
                '}';
    }
}