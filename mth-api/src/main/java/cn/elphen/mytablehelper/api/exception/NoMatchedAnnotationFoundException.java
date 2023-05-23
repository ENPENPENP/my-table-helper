package cn.elphen.mytablehelper.api.exception;

import java.lang.annotation.Annotation;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class NoMatchedAnnotationFoundException extends RuntimeException {

    public NoMatchedAnnotationFoundException(Class<? extends Annotation> annotation, Class<?> targetClass) {
        super(String.format("Can not find specified annotation '%s' in class '%s'", annotation.getName(), targetClass.getName()));
    }
}
