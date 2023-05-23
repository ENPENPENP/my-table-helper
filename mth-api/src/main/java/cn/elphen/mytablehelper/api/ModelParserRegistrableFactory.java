package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * A registrable model parser factory.
 *
 * @author Elphen
 * @see ModelParserFactory
 * @since 0.0.1
 */
public interface ModelParserRegistrableFactory<I, T extends ModelParserRegistrableFactory<I, T>>
        extends ModelParserFactory {

    /**
     * Register a ModelParser with specified ModelType.
     *
     * @param modelType type of model, defined by {@link ModelType}
     * @param instance  ModelParser instance to be registered
     * @return Registry instance
     * @see ModelType
     */
    T register(ModelType modelType, I instance);

    /**
     * Unregister a ModelParser with specified ModelType.
     *
     * @param modelType type of model, defined by {@link ModelType}
     * @param instance  ModelParser instance to be unregistered
     * @return Registry instance
     */
    T unregister(ModelType modelType, I instance);

    /**
     * Unregister all ModelParser instance.
     *
     * @return Registry instance
     */
    T clear();

}
