package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class OrderPassport extends BaseEntity {

    private Long orderId;
    private Long passportId;

    public OrderPassport() {
        super();
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
        if (!super.equals(o)) return false;
        OrderPassport that = (OrderPassport) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(passportId, that.passportId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), orderId, passportId);
    }
}