package cn.elphen.mytablehelper.api;

/**
 * Provide the function to identify different service when maybe existing more than one service support same platform.
 * Note that a service must be distinguished by supported <code>platformName</code> and a custom <code>componentId</code>.
 *
 * @author Elphen
 * @since 0.0.1
 */
public interface ServiceIdentify {

    /**
     * Return boolean result about if the service can the provided support platform.
     *
     * @param platformName name of platform i.g. MySql
     * @return true if supported
     */
    boolean isSupportPlatform(String platformName);

    /**
     * Get the custom component id of the service.
     *
     * @return custom component id
     */
    String getComponentId();

    /**
     * TODO: considering compare version when getting service instance.
     * Get the required API version of this service.
     *
     * @return required API version
     * @see Version
     */
    String getRequiredVersion();
}
