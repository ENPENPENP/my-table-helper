package cn.elphen.mytablehelper.api.exception;

/**

 * @author Elphen
 * @since  0.0.1
 */
public class AnnotationAccessException extends RuntimeException {

    public AnnotationAccessException(Object object) {
        super(String.format("Could not access annotation from object type '%s'", object.getClass().getName()));
    }
}
