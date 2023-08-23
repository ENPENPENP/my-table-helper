package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.MetaObject;
import cn.elphen.mytablehelper.api.support.constant.MetaDataType;
import cn.elphen.mytablehelper.api.util.ReflectionUtil;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Elphen Liu
 * @since  0.0.1
 */

public abstract class AbstractMetaObjectHolder implements MetaObject {

    private final Object source;
    private final MetaDataType type;
    private final boolean cloned;

    public AbstractMetaObjectHolder(final Object source, final MetaDataType type) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("Source object is null");
        }
        Object cloneObject = null;
        boolean cloned = false;
        if (type.equals(MetaDataType.OBJECT)) {
            try {
                // try to invoke clone() if object implement Cloneable interface.
                if (source.getClass().isAssignableFrom(Cloneable.class)) {
                    cloneObject = getClonedObject();
                    cloned = true;
                }
            } catch (Exception e) {
                // ignored it.
            }
        }
        if (null == cloneObject) {
            cloneObject = source;
            cloned = false;
        }
        this.cloned = cloned;
        this.source = cloneObject;
        this.type = type;
    }

    public AbstractMetaObjectHolder(final Object source) {
        if (Objects.isNull(source)) {
            throw new IllegalArgumentException("Source object is null");
        }
        if (source instanceof Class) {
            this.type = MetaDataType.OBJECT_TYPE;
            this.cloned = false;
            this.source = source;
        } else if (source instanceof Field) {
            this.type = MetaDataType.TYPE_FIELD;
            this.cloned = false;
            this.source = source;
        } else {
            Object cloneObject = null;
            boolean cloned = false;
            try {
                // try to invoke clone() if object implement Cloneable interface.
                if (source.getClass().isAssignableFrom(Cloneable.class)) {
                    cloneObject = ReflectionUtil.invokeMethod(source, "clone");
                    cloned = true;
                }
            } catch (Exception e) {
                // ignored it.
            }
            if (null == cloneObject) {
                cloneObject = source;
                cloned = false;
            }
            this.cloned = cloned;
            this.source = cloneObject;
            this.type = MetaDataType.OBJECT;
        }
    }

    @Override
    public Class<?> getSourceClass() {
        if (this.type.equals(MetaDataType.OBJECT_TYPE)) {
            return (Class<?>) this.source;
        }
        return this.source.getClass();
    }

    @Override
    public Object getSource() {
        return this.source;
    }

    @Override
    public MetaDataType getType() {
        return this.type;
    }

    public boolean isCloned() {
        return cloned;
    }

    protected Object getClonedObject() {
        return ReflectionUtil.invokeMethod(source, "clone");
    }
}
