package exceptions;

public class DocumentCreateException extends RuntimeException {

    public DocumentCreateException(String message) {
        super(message);
    }
}