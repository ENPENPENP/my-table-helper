package cn.elphen.mytablehelper.api.support;

import cn.elphen.mytablehelper.api.Wrapper;
import cn.elphen.mytablehelper.api.exception.ObjectCastException;

/**
 * @author Elphen
 * @since 0.0.1
 */
public abstract class AbstractWrapper implements Wrapper {

    @Override
    public <T> T unwrapFor(Class<T> targetClass) {
        try {
            return targetClass.cast(this);
        } catch (ClassCastException exception) {
            throw new ObjectCastException("Unable to case the object to '" + targetClass.getName() +"'");
        }
    }

    @Override
    public boolean isWrapFor(Class<?> targetClass) {
        return targetClass.isInstance(this);
    }
}
