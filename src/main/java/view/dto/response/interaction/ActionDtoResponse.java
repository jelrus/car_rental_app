package view.dto.response.interaction;

import persistence.entity.interaction.Action;
import view.dto.response.DtoResponse;

import java.util.Objects;

public class ActionDtoResponse extends DtoResponse {

    private String identifier;
    private String message;
    private Boolean enabled;

    public ActionDtoResponse(Action action) {
        super(action.getId(), action.getCreated(), action.getUpdated(), action.getVisible());
        setIdentifier(action.getIdentifier());
        setMessage(action.getMessage());
        setEnabled(action.getEnabled());
    }

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
        if (!super.equals(o)) return false;
        ActionDtoResponse that = (ActionDtoResponse) o;
        return Objects.equals(identifier, that.identifier) && Objects.equals(message, that.message) && Objects.equals(enabled, that.enabled);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, message, enabled);
    }

    @Override
    public String toString() {
        return "ActionDtoResponse{" + super.toString() +
                "identifier='" + identifier + '\'' +
                ", message='" + message + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}