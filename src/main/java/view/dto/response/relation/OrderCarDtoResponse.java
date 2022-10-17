package view.dto.response.relation;

import persistence.entity.relation.OrderCar;
import view.dto.response.DtoResponse;

import java.util.Date;
import java.util.Objects;

public class OrderCarDtoResponse extends DtoResponse {

    private Long orderId;
    private Long carId;

    public OrderCarDtoResponse(OrderCar oCar) {
        super(oCar.getId(), oCar.getCreated(), oCar.getUpdated(), oCar.getVisible());
        setOrderId(oCar.getOrderId());
        setCarId(oCar.getCarId());
    }

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
        OrderCarDtoResponse that = (OrderCarDtoResponse) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(carId, that.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, carId);
    }

    @Override
    public String toString() {
        return "OrderCarDtoResponse{" +
                "orderId=" + orderId +
                ", carId=" + carId +
                '}';
    }
}
