package persistence.entity.interaction.type;

public enum OrderStatus {

    PROCESSING("Processing"),
    APPROVED("Approved"),
    REJECTED("Rejected"),
    ACTIVE("Active"),
    CAR_INSPECTION("Car Inspection"),
    DAMAGE_REFUND("Damage Refund"),
    COMPLETED("Completed");

    private final String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }
}