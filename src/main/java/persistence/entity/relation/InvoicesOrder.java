package persistence.entity.relation;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class InvoicesOrder extends BaseEntity {

    private String fileLink;
    private Long orderId;
    private Boolean enabled;

    public InvoicesOrder() {
        super();
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        InvoicesOrder that = (InvoicesOrder) o;
        return Objects.equals(fileLink, that.fileLink) && Objects.equals(orderId, that.orderId) && Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fileLink, orderId, enabled);
    }

    @Override
    public String toString() {
        return "InvoicesOrder{" + super.toString() +
                "fileLink='" + fileLink + '\'' +
                ", orderId=" + orderId +
                ", enabled=" + enabled +
                '}';
    }
}