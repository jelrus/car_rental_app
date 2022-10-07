package persistence.entity.relation;

import persistence.entity.BaseEntity;
import persistence.entity.annotations.Column;
import persistence.entity.annotations.MergeField;
import persistence.entity.annotations.Table;

import java.util.Objects;

@Table(tableName = "order_car")
public class OrderCar extends BaseEntity {

    @Column(name = "order_id")
    private Long orderId;

    @MergeField(name = "car_id")
    @Column(name = "car_id")
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

    @Override
    public String toString() {
        return "OrderCar{" + super.toString() +
                "orderId=" + orderId +
                ", carId=" + carId +
                '}';
    }
}