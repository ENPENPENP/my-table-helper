package cn.elphen.mytablehelper.api.exception;

/**
 * @author elhpen
 * @since  0.0.1
 */
public class NoConstructorException extends RuntimeException {

    public NoConstructorException(Class<?> targetClass) {
        super("MTH: No constructor found for object type " + targetClass.getName());
    }

    public NoConstructorException(String className) {
        super("MTH: No constructor found for object type " + className);
    }

}