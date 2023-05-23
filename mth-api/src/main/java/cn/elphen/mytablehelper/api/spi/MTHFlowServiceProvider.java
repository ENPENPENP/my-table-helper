package cn.elphen.mytablehelper.api.spi;

import cn.elphen.mytablehelper.api.Flow;
import cn.elphen.mytablehelper.api.ServiceIdentify;

/**
 * An SPI service definition interface. Invoker can get all implemented service by {@link java.util.ServiceLoader}.
 * <p>
 * More information can see in <a href="https://docs.oracle.com/javase/tutorial/sound/SPI-intro.html">
 * * Introduction to the Service Provider Interfaces</a>.
 * </p>
 *
 * <p>
 * Note that the implementation must provide supported <code>platformName</code> and <code>componentId</code> to identify from other service.
 * </p>
 *
 * @author Elphen
 * @see java.util.ServiceLoader
 * @since 0.0.1
 */
public interface MTHFlowServiceProvider extends ServiceIdentify {

    /**
     * Initialize the service provider.
     */
    void initialize();

    /**
     * Get flow instance.
     *
     * @return flow instance
     * @see Flow
     */
    Flow getFlow();

}
