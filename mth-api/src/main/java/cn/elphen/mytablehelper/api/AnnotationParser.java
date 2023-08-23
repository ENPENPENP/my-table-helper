package cn.elphen.mytablehelper.api;

import java.lang.annotation.Annotation;

/**
 * AnnotationParser provide the ability to parse specified <code>Annotation</code> type Class to specified <code>Model</code>.
 *
 * @param <A> any annotation type
 * @param <M> the generic type implements or extends {@link Model}
 * @author Elphen Liu
 * @see Model
 * @see Annotation
 * @since 0.0.1
 */
public interface AnnotationParser<M extends Model, A extends Annotation> {

    /**
     * Verify the provided object was marked with specified annotation.
     *
     * @param source provided object
     * @return true if the object declared with specified annotation
     */
    boolean verifyAnnotation(Object source);

    /**
     * Return boolean result that if this parser can parse the provided annotation class.
     *
     * @param annotationClass any annotation class
     * @return true if support
     */
    boolean support(Class<? extends Annotation> annotationClass);

    /**
     * Get the Class of supported model.
     *
     * @return Class of model
     */
    Class<M> getModelClass();

    /**
     * Get the Class of supported annotation.
     *
     * @return Class of annotation
     */
    Class<A> getAnnotationClass();

    /**
     * Try getting specified annotation from provided object and parse it to build specified {@link Model} object.
     *
     * @param source provided object
     * @return the specified type model object
     * @see Model
     */
    M parse(Object source);
}
