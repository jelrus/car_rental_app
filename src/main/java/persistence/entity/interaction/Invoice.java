package persistence.entity.interaction;

import persistence.entity.BaseEntity;

import java.util.Objects;

public class Invoice extends BaseEntity {

    private String identifier;
    private String message;

    public Invoice() {
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
        Invoice invoice = (Invoice) o;
        return Objects.equals(identifier, invoice.identifier) &&
               Objects.equals(message, invoice.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), identifier, message);
    }
}