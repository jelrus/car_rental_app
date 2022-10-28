package view.dto.request.interaction;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class ActionDtoRequest extends DtoRequest {

    private String identifier;
    private String message;
    private Boolean enabled;

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        ActionDtoRequest that = (ActionDtoRequest) o;
        return Objects.equals(identifier, that.identifier) &&
               Objects.equals(message, that.message) &&
               Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier, message, enabled);
    }

    @Override
    public String toString() {
        return "ActionDtoRequest{" +
                "identifier='" + identifier + '\'' +
                ", message='" + message + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}