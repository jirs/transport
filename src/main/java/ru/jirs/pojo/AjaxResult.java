package ru.jirs.pojo;

public class AjaxResult<T> {
    private Status status;
    private String statusText;
    private T result;

    public AjaxResult(Status status, String statusText, T result) {
        this.status = status;
        this.statusText = statusText;
        this.result = result;
    }

    public AjaxResult(Status status, String statusText) {
        this.status = status;
        this.statusText = statusText;
    }

    public String getStatus() {
        return status.name();
    }

    public String getStatusText() {
        return statusText;
    }

    public T getResult() {
        return result;
    }

    public enum Status {
        OK, FAIL;
    }
}
