package com.blogapplication.api.payloads;

public class APIREsponse {
    private String message;
    private Boolean success;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public APIREsponse(String message, Boolean success) {
        this.message = message;
        this.success = success;
    }
}
