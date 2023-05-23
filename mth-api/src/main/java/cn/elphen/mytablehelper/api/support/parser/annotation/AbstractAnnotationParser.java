package cn.elphen.mytablehelper.api.support.parser.annotation;

import java.lang.annotation.Annotation;

import cn.elphen.mytablehelper.api.AnnotationParser;
import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.exception.ObjectInstantException;
import cn.elphen.mytablehelper.api.util.ReflectionUtil;

/**
 * An abstracted implement of AnnotationParser.
 *
 * @author Elphen Liu
 * @since  0.0.1
 */
public abstract class AbstractAnnotationParser<M extends Model, A extends Annotation> implements AnnotationParser<M, A> {

    @Override
    public boolean verify(Object source) {
        return ReflectionUtil.hasAnnotation(source, getAnnotationClass());
    }

    @Override
    public boolean support(Class<? extends Annotation> annotationClass) {
        return ReflectionUtil.isExtOrImplOf(getAnnotationClass(), annotationClass);
    }

    @Override
    public Class<M> getModelClass() {
        return ReflectionUtil.getSuperclass(this.getClass(), 0);
    }

    @Override
    public Class<A> getAnnotationClass() {
        return ReflectionUtil.getSuperclass(this.getClass(), 1);
    }
}
