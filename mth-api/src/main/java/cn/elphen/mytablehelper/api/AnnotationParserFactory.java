package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.exception.AnnotationAccessException;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;

/**
 * AnnotationParserFactory provide <code>AnnotationParser</code> for specified object.
 *
 * @author Elphen Liu
 * @see AnnotationParser
 * @since 0.0.1
 */
public interface AnnotationParserFactory {

    /**
     * Assign an annotation parser to execute for an object, if the object used an annotation supported by an annotation parser.
     *
     * @param source source object
     * @return the supported annotation parser
     * @throws AnnotationAccessException can not access the annotations of parameter object
     * @throws NoMatchedParserFoundException no matched Parser found
     */
    AnnotationParser<?, ?> getParser(final Object source);
}
