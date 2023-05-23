package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.support.constant.MetaDataType;

/**
 * @author Elphen Liu
 * @since  0.0.1
 */

public class GenericMetaObjectHolder extends AbstractMetaObjectHolder {

    public GenericMetaObjectHolder(final Object source, final MetaDataType type) {
        super(source, type);
    }

    public GenericMetaObjectHolder(final Object source) {
        super(source);
    }
}
