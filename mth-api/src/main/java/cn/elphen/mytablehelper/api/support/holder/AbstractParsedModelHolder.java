package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.support.flow.AbstractWrapper;
import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.ParsedModelHolder;
import cn.elphen.mytablehelper.api.MetaObject;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * @author Elphen Liu
 * @since  0.0.1
 */

public abstract class AbstractParsedModelHolder<M extends Model> extends AbstractWrapper implements ParsedModelHolder<M> {

    private final M model;
    private final ModelType type;
    private MetaObject metaObject;

    public AbstractParsedModelHolder(M model, MetaObject metaObject) {
        this.model = model;
        this.type = this.model.getModelType();
        this.metaObject = metaObject;
    }

    public AbstractParsedModelHolder(M model) {
        this.model = model;
        this.type = model.getModelType();
    }

    protected boolean refresh() {
        return true;
    }

    @Override
    public M getModel(){
        return this.model;
    }

    @Override
    public ModelType getType() {
        return this.type;
    }

    @Override
    public MetaObject getMetaObject(){
        return this.metaObject;
    }
}
