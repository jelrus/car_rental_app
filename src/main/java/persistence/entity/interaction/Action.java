package persistence.entity.interaction;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class Action extends BaseEntity {

    private String identifier;
    private String message;

    public Action() {
        super();
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Action action = (Action) o;
        return Objects.equals(identifier, action.identifier) &&
               Objects.equals(message, action.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, message);
    }
}