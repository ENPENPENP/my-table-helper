package cn.elphen.mytablehelper.api.support.handler;

import cn.elphen.mytablehelper.api.Entity;
import cn.elphen.mytablehelper.api.EntityHandler;
import cn.elphen.mytablehelper.api.ModelParserFactory;

/**
 * An abstracted implement of EntityHandler.
 * @author Elphen
 * @since 0.0.1
 */
public abstract class AbstractEntityHandler<E extends Entity> implements EntityHandler<E> {

    protected final ModelParserFactory modelParserFactory;

    public AbstractEntityHandler(ModelParserFactory modelParserFactory) {
        this.modelParserFactory = modelParserFactory;
    }

}
