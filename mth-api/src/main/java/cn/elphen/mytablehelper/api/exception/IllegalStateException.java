package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class IllegalStateException extends RuntimeException {

    public IllegalStateException() {
    }

    public IllegalStateException(String message) {
        super(message);
    }

    public IllegalStateException(String message, Throwable cause) {
        super(message, cause);
    }
}
