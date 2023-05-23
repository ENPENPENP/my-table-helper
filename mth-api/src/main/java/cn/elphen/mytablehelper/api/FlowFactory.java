package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.exception.NoImplementationFoundException;
import cn.elphen.mytablehelper.api.spi.MTHFlowServiceProvider;
import cn.elphen.mytablehelper.api.util.PrintUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

/**
 * FlowFactory provided {@link Flow} instance with different <code>platform name</code> and <code>componentId</code>.
 *
 * <p>
 * The method to get <code>platform name</code> and <code>componentId</code> were declared in {@link ServiceIdentify},
 * it mean you can get a specified Flow by <code>platform name</code> or <code>componentId</code>.
 * </p>
 *
 * @author Elphen Liu
 * @see Flow
 * @see ServiceIdentify
 * @since 0.0.1
 */
public final class FlowFactory {

    /**
     * Get a flow instance which supported provided platform.
     *
     * @param platform target platform
     * @return Return a flow instance if any matched instance existed
     * @throws NoImplementationFoundException no matched flow instance found
     */
    public static Flow getFlow(String platform) {
        return getFlow(platform, null);
    }

    /**
     * Get a flow instance which is an instance or implementation instance of the specified class.
     *
     * @param targetClass target Class
     * @return Return a flow instance if any matched instance existed
     * @throws NoImplementationFoundException no matched flow instance found
     */
    public static Flow getFlow(Class<? extends Flow> targetClass) {
        return getFlow(null, null, targetClass);
    }

    /**
     * Get a flow instance which supported provided platform and has specified componentId.
     *
     * @param platform    target platform
     * @param componentId componentId of
     * @return Return a flow instance if any matched instance existed
     * @throws NoImplementationFoundException no matched flow instance found
     */
    public static Flow getFlow(String platform, String componentId) {
        return getFlow(platform, componentId, null);
    }

    /**
     * Get a flow instance which supported provided platform and has specified componentId,
     * and it must be an instance or implementation instance of the specified class.
     *
     * @param platform    target platform
     * @param componentId componentId of
     * @param targetClass target Class
     * @return Return a flow instance if any matched instance existed
     * @throws NoImplementationFoundException no matched flow instance found
     */
    public static Flow getFlow(String platform, String componentId, Class<? extends Flow> targetClass) {
        return getFlowFromImplement(platform, componentId, targetClass);
    }


    private static Flow getFlowFromImplement(final String platform, final String componentId, final Class<?> targetClass) {
        final String _platform = !"".equals(platform) ? platform : null;
        final Class<?> _targetClass = targetClass;
        final String _componentId = !"".equals(componentId) ? componentId : null;

        ServiceLoader<MTHFlowServiceProvider> serviceLoader = getServiceLoader(FlowFactory.class.getClassLoader());
        List<MTHFlowServiceProvider> serviceProviders = getServiceProviders(serviceLoader);
        MTHFlowServiceProvider targetProvider = null;

        for (MTHFlowServiceProvider serviceProvider : serviceProviders) {

            if (_platform != null && !serviceProvider.isSupportPlatform(_platform)) {
                continue;
            }
            if (_targetClass != null
                    && !_targetClass.isAssignableFrom(serviceProvider.getFlow().getClass())) {
                continue;
            }
            if (_componentId != null && !_componentId.equals(serviceProvider.getComponentId())) {
                continue;
            }

            targetProvider = serviceProvider;
            break;
        }

        if (targetProvider == null) {
            StringBuilder stringBuilder = new StringBuilder("No provider found with");
            PrintUtil.printMsg("No provider found with");
            if (_platform != null) {
                stringBuilder.append(" platform '").append(_platform).append("'");
            }
            if (_targetClass != null) {
                stringBuilder.append(" class '").append(_targetClass.getName()).append("'");
            }
            if (_componentId != null) {
                stringBuilder.append(" componentId '").append(_componentId).append("'");
            }
            throw new NoImplementationFoundException(stringBuilder.toString());
        }

        targetProvider.initialize();

        return targetProvider.getFlow();
    }

    private static ServiceLoader<MTHFlowServiceProvider> getServiceLoader(ClassLoader classLoader) {
        ClassLoader _classLoader;

        if (classLoader != null) {
            _classLoader = classLoader;
        } else {
            _classLoader = FlowFactory.class.getClassLoader();
        }

        return ServiceLoader.load(MTHFlowServiceProvider.class, _classLoader);
    }

    private static List<MTHFlowServiceProvider> getServiceProviders(ServiceLoader<MTHFlowServiceProvider> serviceProvider) {
        List<MTHFlowServiceProvider> serviceProviderList = new ArrayList<>();

        Iterator<MTHFlowServiceProvider> iterator = serviceProvider.iterator();
        while (iterator.hasNext()) {
            try {
                serviceProviderList.add(iterator.next());
            } catch (Exception e) {
                PrintUtil.printException(e);
            }
        }

        return serviceProviderList;
    }

}
