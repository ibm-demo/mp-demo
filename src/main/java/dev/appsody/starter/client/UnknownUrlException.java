package dev.appsody.starter.client;

public class UnknownUrlException extends Exception {

    private static final long serialVersionUID = 1L;

    public UnknownUrlException() {
        super();
    }

    public UnknownUrlException(String message) {
        super(message);
    }
}
