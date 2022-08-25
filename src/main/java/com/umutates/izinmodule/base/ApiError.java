package com.umutates.izinmodule.base;

import org.springframework.http.HttpStatus;

public class ApiError {
    private HttpStatus status;
    private String message;


    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    ApiError(ApiErrorBuilder apiErrorBuilder){
        this.status = apiErrorBuilder.status;
        this.message = apiErrorBuilder.message;
    }

    public static ApiErrorBuilder builder(){
        return new ApiErrorBuilder();
    }

    public static class ApiErrorBuilder{
        private HttpStatus status;
        private String message;

        public ApiErrorBuilder status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public ApiErrorBuilder message(String message) {
            this.message = message;
            return this;
        }

        public ApiError build(){
            return new ApiError(this);
        }


    }
}
