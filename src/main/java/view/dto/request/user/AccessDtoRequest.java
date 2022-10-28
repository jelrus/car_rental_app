package view.dto.request.user;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class AccessDtoRequest extends DtoRequest {

    private Boolean enabled;

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
        AccessDtoRequest that = (AccessDtoRequest) o;
        return Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(enabled);
    }

    @Override
    public String toString() {
        return "AccessDtoRequest{" +
                "enabled=" + enabled +
                '}';
    }
}