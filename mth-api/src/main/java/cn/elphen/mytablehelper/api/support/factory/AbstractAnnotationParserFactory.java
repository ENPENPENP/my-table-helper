package cn.elphen.mytablehelper.api.support.factory;

import cn.elphen.mytablehelper.api.AnnotationParser;
import cn.elphen.mytablehelper.api.AnnotationParserFactory;
import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.exception.AnnotationAccessException;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import cn.elphen.mytablehelper.api.util.ReflectionUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

/**
 * An abstracted implementation of AnnotationParserFactory.
 *
 * @author Elphen Liu
 * @since  0.0.1
 */
public abstract class AbstractAnnotationParserFactory<M extends Model> implements AnnotationParserFactory {

    public abstract void initialize();

    /**
     * Store annotation parser that supported model type.
     */
    private final HashMap<Class<? extends Annotation>, AnnotationParser<M, ?>> supportAnnotationParserMap = new HashMap<>();

    public AbstractAnnotationParserFactory() {
    }

    public AbstractAnnotationParserFactory(AnnotationParser<M, ?> annotationParser) {
        if (null == annotationParser) {
            throw new IllegalArgumentException("Annotation Parser must be not null");
        }
        this.supportAnnotationParserMap.put(annotationParser.getAnnotationClass(), annotationParser);
    }

    public AbstractAnnotationParserFactory(HashMap<Class<? extends Annotation>, AnnotationParser<M, ?>> supportAnnotationParserMap) {
        if (null == supportAnnotationParserMap || supportAnnotationParserMap.isEmpty()) {
            throw new IllegalArgumentException("Please ensure at least one parser to be registered");
        }
        this.supportAnnotationParserMap.putAll(supportAnnotationParserMap);
    }

    public void registerParser(AnnotationParser<M, ?> annotationParser) {
        if (null == annotationParser) {
            throw new IllegalArgumentException("Annotation Parser must be not null");
        }
        this.supportAnnotationParserMap.put(annotationParser.getAnnotationClass(), annotationParser);
    }

    @Override
    public AnnotationParser<M, ?> getParser(final Object source) {
        Annotation[] annotations;

        if (ReflectionUtil.isClass(source)) {
            Class<?> cls = (Class<?>) source;
            annotations = cls.getAnnotations();
        }
        else if (ReflectionUtil.isExtOrImplOf(source, AnnotatedElement.class)) {
            AnnotatedElement annotatedElement = (AnnotatedElement) source;
            annotations = annotatedElement.getAnnotations();
        }
        else {
            // type of object unsupported for getting annotation.
            throw new AnnotationAccessException(source);
        }

        // no annotation found.
        if (annotations == null || annotations.length == 0) {
            throw new RuntimeException("No annotation found from object.");
        }

        Set<Class<? extends Annotation>> supportAnnotations = this.supportAnnotationParserMap.keySet();
        Class<? extends Annotation> targetAnnotation = supportAnnotations
                .stream()
                .filter(sa -> Arrays.stream(annotations).anyMatch(a -> a.annotationType().equals(sa)))
                .findFirst()
                .orElseThrow(() -> new NoMatchedParserFoundException(getClassOfSourceObject(source)));
        return this.supportAnnotationParserMap.get(targetAnnotation);
    }

    private Class<?> getClassOfSourceObject(Object object) {
        if (object instanceof Class) {
            return (Class<?>) object;
        } else {
            return object.getClass();
        }
    }
}
