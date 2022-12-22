package view.dto.request.relation;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class InvoicesOrderDtoRequest extends DtoRequest {

    private String fileLink;
    private Long orderId;
    private Boolean enabled;

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
        InvoicesOrderDtoRequest that = (InvoicesOrderDtoRequest) o;
        return Objects.equals(fileLink, that.fileLink) &&
               Objects.equals(orderId, that.orderId) &&
               Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileLink, orderId, enabled);
    }

    @Override
    public String toString() {
        return "InvoicesOrderDtoRequest{" + super.toString() +
                "fileLink='" + fileLink + '\'' +
                ", orderId=" + orderId +
                ", enabled=" + enabled +
                '}';
    }
}