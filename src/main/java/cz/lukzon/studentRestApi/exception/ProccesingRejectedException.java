package cz.lukzon.studentRestApi.exception;

public class ProccesingRejectedException extends Exception {

    public ProccesingRejectedException() {
        super();
    }

    public ProccesingRejectedException(String message) {
        super(message);
    }

    public ProccesingRejectedException(Throwable cause) {
        super(cause);
    }

    public ProccesingRejectedException(String message, Throwable cause) {
        super(message, cause);
    }
}
