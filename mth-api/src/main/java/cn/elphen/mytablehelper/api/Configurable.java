package cn.elphen.mytablehelper.api;

import java.util.Map;

/**
 * Configurable abstracted some function for a component which is configurable.
 *
 * @author Elphen Liu
 * @since 0.0.1
 */
public interface Configurable {

    /**
     * Set an existed Context, the implemented Class can be load the config from it.
     *
     * @param context context from other component
     */
    void setContext(Map<String, Object> context);

    /**
     * Get a copy Context from existed Context.
     * @return the copy of exited context
     */
    Map<String, Object> getCopyOfContext();

    /**
     * Set config with value object.
     *
     * @param option option name
     * @param value  value
     */
    void setConfig(String option, Object value);

    /**
     * Unset a config with option name.
     *
     * @param option option name
     */
    void unsetConfig(String option);

}
