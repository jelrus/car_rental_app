package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class OrderCarDtoRequest extends DtoRequest {

    private Long orderId;
    private Long carId;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderCarDtoRequest that = (OrderCarDtoRequest) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, carId);
    }

    @Override
    public String toString() {
        return "OrderCarDtoRequest{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                '}';
    }
}
