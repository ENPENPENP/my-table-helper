package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 * @since  0.0.1
 */
public class InitializeFailedException extends RuntimeException {

    public InitializeFailedException(String message) {
        super(message);
    }

    public InitializeFailedException(Throwable cause) {
        super(cause);
    }
}
