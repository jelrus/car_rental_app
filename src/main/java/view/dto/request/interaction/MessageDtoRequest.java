package view.dto.request.interaction;

import view.dto.request.DtoRequest;

import java.util.Objects;

public class MessageDtoRequest extends DtoRequest {

    private String message;

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
        MessageDtoRequest that = (MessageDtoRequest) o;
        return Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message);
    }

    @Override
    public String toString() {
        return "MessageDtoRequest{" +
                "message='" + message + '\'' +
                '}';
    }
}