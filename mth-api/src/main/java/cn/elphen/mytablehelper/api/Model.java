package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.support.constant.ModelType;

import java.io.Serializable;

/**
 * <code>Model</code> mark a Class can be input into any parser or component in mytablehelper.
 * The implement of <code>Model</code> should declare some fields to storage the values for building a completed database script i.g.
 * table name, column name or column value type.
 *
 * @author Elphen Liu
 * @see ModelParser
 * @see AnnotationParser
 * @since 0.0.1
 */
public interface Model extends Cloneable, Wrapper, Serializable {

    /**
     * Type of model, defined by {@link ModelType}.
     *
     * @return type of model
     */
    ModelType getModelType();

    /**
     * Can the application cache the model object.
     *
     * @return true if it can be cached
     */
    boolean cacheAble();
}
