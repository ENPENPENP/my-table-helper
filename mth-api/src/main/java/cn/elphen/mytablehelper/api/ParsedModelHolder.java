package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.support.constant.ModelType;

import java.io.Serializable;

/**
 * A container for parsed model.
 *
 * @author Elphen
 * @since 0.0.1
 */
public interface ParsedModelHolder<T extends Model> extends Wrapper, Serializable {

    /**
     * Get model object.
     *
     * @return Model Object
     * @see Model
     */
    T getModel();

    /**
     * Get model type
     *
     * @return model type
     * @see ModelType
     */
    ModelType getType();

    /**
     * Get source object container.
     *
     * @return source object container.
     * @see MetaObject
     */
    MetaObject getMetaObject();

}
