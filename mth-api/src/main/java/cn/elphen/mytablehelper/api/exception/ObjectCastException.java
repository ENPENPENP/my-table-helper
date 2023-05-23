package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class ObjectCastException extends RuntimeException {

    public ObjectCastException(String message) {
        super(message);
    }

    public ObjectCastException(String message, Throwable cause) {
        super(message, cause);
    }
}
