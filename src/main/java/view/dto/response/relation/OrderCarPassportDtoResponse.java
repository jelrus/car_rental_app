package view.dto.response.relation;

import persistence.entity.relation.OrderCarPassport;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class OrderCarPassportDtoResponse extends DtoResponse {

    private Long orderId;
    private Long carId;
    private Long passportId;

    public OrderCarPassportDtoResponse(OrderCarPassport orderCarPassport) {
        super(orderCarPassport.getId(), orderCarPassport.getCreated(), orderCarPassport.getUpdated(), orderCarPassport.getVisible());
        setOrderId(orderCarPassport.getOrderId());
        setCarId(orderCarPassport.getCarId());
        setPassportId(orderCarPassport.getPassportId());
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
        OrderCarPassportDtoResponse that = (OrderCarPassportDtoResponse) o;
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
        return "OrderCarPassportDtoResponse{" + super.toString() +
                "orderId=" + orderId +
                ", carId=" + carId +
                ", passportId=" + passportId +
                '}';
    }
}