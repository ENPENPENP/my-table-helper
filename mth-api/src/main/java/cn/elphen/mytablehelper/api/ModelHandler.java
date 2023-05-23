package cn.elphen.mytablehelper.api;

/**
 * ModelHandler provided a abstract function for parse an object with specified model type.
 *
 * @author Elphen Liu
 * @since 0.0.1
 */
public interface ModelHandler<M extends Model> {

    /**
     * Parse an object with specified model type and return a model
     *
     * @param modelClass the type of model
     * @param metaObject source object container
     * @return Model object
     * @see MetaObject
     * @see Model
     */
    M handle(final Class<M> modelClass, final MetaObject metaObject);
}
