package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class OrderCarPassport extends BaseEntity {

    private Long orderId;
    private Long carId;
    private Long passportId;

    public OrderCarPassport() {
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
        if (!super.equals(o)) return false;
        OrderCarPassport that = (OrderCarPassport) o;
        return Objects.equals(orderId, that.orderId) &&
               Objects.equals(carId, that.carId) &&
               Objects.equals(passportId, that.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, carId, passportId);
    }

    @Override
    public String toString() {
        return "OrderCarPassport{" + super.toString() +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", passportId=" + passportId +
                '}';
    }
}