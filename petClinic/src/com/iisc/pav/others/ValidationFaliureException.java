package com.iisc.pav.others;

public class ValidationFaliureException extends RuntimeException{
    public ValidationFaliureException() {
    }

    public ValidationFaliureException(String s) {
        super(s);
    }

    public ValidationFaliureException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public ValidationFaliureException(Throwable throwable) {
        super(throwable);
    }

    public ValidationFaliureException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}
