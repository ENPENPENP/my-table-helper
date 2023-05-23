package cn.elphen.mytablehelper.api.exception;

/**
 * @author Elphen
 */
public class InvalidEntityException extends RuntimeException {

    public InvalidEntityException() {
    }

    public InvalidEntityException(String message) {
        super(message);
    }

    public InvalidEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
