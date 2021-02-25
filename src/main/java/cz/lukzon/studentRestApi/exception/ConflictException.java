package cz.lukzon.studentRestApi.exception;

public class ConflictException extends Exception {

    public ConflictException() {
        super();
    }

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(Throwable cause) {
        super(cause);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
