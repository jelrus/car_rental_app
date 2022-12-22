package view.dto.response.relation;

import persistence.entity.relation.InvoicesOrder;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class InvoicesOrderDtoResponse extends DtoResponse {

    private String fileLink;
    private Long orderId;
    private Boolean enabled;

    public InvoicesOrderDtoResponse(InvoicesOrder invoicesOrder) {
        super(invoicesOrder.getId(), invoicesOrder.getCreated(), invoicesOrder.getUpdated(), invoicesOrder.getVisible());
        setFileLink(invoicesOrder.getFileLink());
        setEnabled(invoicesOrder.getEnabled());
        setOrderId(invoicesOrder.getOrderId());
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
        InvoicesOrderDtoResponse that = (InvoicesOrderDtoResponse) o;
        return Objects.equals(fileLink, that.fileLink) &&
               Objects.equals(orderId, that.orderId) &&
               Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), fileLink, orderId, enabled);
    }

    @Override
    public String toString() {
        return "InvoicesOrderDtoResponse{" +
                "fileLink='" + fileLink + '\'' +
                ", orderId=" + orderId +
                ", enabled=" + enabled +
                '}';
    }
}