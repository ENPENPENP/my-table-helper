package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.support.constant.MetaDataType;

/**
 * A container for any object. It provides some function such as storage source object, extract and get object type.
 *
 * @author Elphen Liu
 * @see MetaDataType
 * @since 0.0.1
 */
public interface MetaObject {

    /**
     * Get the type of source object.
     *
     * @return Class type
     */
    Class<?> getSourceClass();

    /**
     * Get source object
     *
     * @return source object
     */
    Object getSource();

    /**
     * Get the {@link MetaDataType} of source object.
     *
     * @return meta data type
     * @see MetaDataType
     */
    MetaDataType getType();
}
