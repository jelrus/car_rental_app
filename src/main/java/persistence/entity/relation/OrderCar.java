package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class OrderCar extends BaseEntity {

    private Long orderId;
    private Long carId;

    public OrderCar() {
        super();
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
        if (!super.equals(o)) return false;
        OrderCar orderCar = (OrderCar) o;
        return Objects.equals(orderId, orderCar.orderId) && Objects.equals(carId, orderCar.carId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, carId);
    }
}