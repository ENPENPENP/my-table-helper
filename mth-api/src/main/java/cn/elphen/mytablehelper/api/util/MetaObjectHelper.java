package cn.elphen.mytablehelper.api.util;

import cn.elphen.mytablehelper.api.MetaObject;
import cn.elphen.mytablehelper.api.support.constant.MetaDataType;
import cn.elphen.mytablehelper.api.support.holder.GenericMetaObjectHolder;

import java.lang.reflect.Field;

/**
 * @author Elphen Liu
 * @since 0.0.1
 */

public class MetaObjectHelper {

    public static MetaObject[] wrap(Field[] fields) {
        return wrap(MetaDataType.TYPE_FIELD, fields);
    }

    public static MetaObject[] wrap(MetaDataType metaDataType, Object[] objects) {
        MetaObject[] metaData = new MetaObject[objects.length];
        for (int i = 0; i < objects.length; i++) {
            metaData[i] = wrap(metaDataType, objects[i]);
        }
        return metaData;
    }

    public static MetaObject wrap(MetaDataType metaDataType, Object object) {
        return new GenericMetaObjectHolder(object, metaDataType);
    }
}
