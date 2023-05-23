package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * ModelParserFactory provide <code>ModelParser</code> for specified object and <code>ModelType</code>.
 *
 * @author Elphen
 * @see ModelParser
 * @see ModelType
 * @since 0.0.1
 */
public interface ModelParserFactory {

    /**
     * Do any operation to initialize the factory.
     */
    void initialize();

    /**
     * Get ModelParser by specified ModelType.
     *
     * @param modelType type of model, defined by {@link ModelType}.
     * @return supported ModelParser
     * @throws NoMatchedParserFoundException when no matched model parser found
     * @see ModelType
     */
    ModelParser<?> getParser(ModelType modelType);

    /**
     * Get ModelParser by specified model Class.
     *
     * @param modelClass model Class
     * @param <T>        the generic type that implements or extends {@link Model}
     * @return model class
     * @throws NoMatchedParserFoundException when no matched model parser found
     */
    <T extends Model> ModelParser<T> getParser(Class<T> modelClass);

    /**
     * Get ModelParser by specified ModelType and model Class.
     *
     * @param modelType  type of model, defined by {@link ModelType}.
     * @param modelClass model Class
     * @param <T>        the generic type that implements or extends {@link Model}
     * @return supported ModelParser
     * @throws NoMatchedParserFoundException when no matched model parser found
     */
    <T extends Model> ModelParser<T> getParser(ModelType modelType, Class<T> modelClass);


    // TODO : exposure more method for get factory information
//    Map<ModelType, Set<Class<? extends Annotation>>> getSupportedTypesAndAnnotations();
}
