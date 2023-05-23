package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 */
public class UnsupportedEntityException extends RuntimeException {

    public UnsupportedEntityException() {
    }

    public UnsupportedEntityException(String message) {
        super(message);
    }

    public UnsupportedEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
