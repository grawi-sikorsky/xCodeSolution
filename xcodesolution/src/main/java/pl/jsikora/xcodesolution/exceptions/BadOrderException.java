package pl.jsikora.xcodesolution.exceptions;

public class BadOrderException extends RuntimeException{
    public BadOrderException(String msg) {
        super(msg);
    }
}