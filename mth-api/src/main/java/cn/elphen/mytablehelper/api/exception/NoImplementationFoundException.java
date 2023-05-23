package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 * @since  0.0.1
 */
public class NoImplementationFoundException extends RuntimeException {

    public NoImplementationFoundException() {
    }

    public NoImplementationFoundException(String message) {
        super(message);
    }

    public NoImplementationFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
