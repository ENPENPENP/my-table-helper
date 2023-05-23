package cn.elphen.mytablehelper.api;

/**
 * Parser for parse a {@link MetaObject} and generate a specified type model object.
 *
 * @param <T> the generic type which must implement {@link Model}
 * @author Elphen Liu
 * @see Model
 * @see MetaObject
 * @since 0.0.1
 */
public interface ModelParser<T extends Model> {

    /**
     * Parse the meta object and build a model object.
     *
     * @param metaObject source object container
     * @return parsed model object
     */
    T parse(MetaObject metaObject);

    /**
     * Return boolean result that if the ModelParser support the provided model Class.
     *
     * @param modelClass model Class
     * @return true it supported
     */
    boolean isSupport(Class<? extends Model> modelClass);
}
