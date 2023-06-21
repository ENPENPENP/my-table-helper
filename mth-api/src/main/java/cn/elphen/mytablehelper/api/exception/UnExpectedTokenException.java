package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 */
public class UnExpectedTokenException extends RuntimeException {

    public UnExpectedTokenException() {
    }

    public UnExpectedTokenException(String message) {
        super(message);
    }

    public UnExpectedTokenException(String message, Throwable cause) {
        super(message, cause);
    }
}
