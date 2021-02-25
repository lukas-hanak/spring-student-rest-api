package cz.lukzon.studentRestApi.dto;

public class BasicInfoDTO {
    private String message;
    private boolean success;
    private int status;

    public BasicInfoDTO(String message, boolean success, int status) {
        this.message = message;
        this.success = success;
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
