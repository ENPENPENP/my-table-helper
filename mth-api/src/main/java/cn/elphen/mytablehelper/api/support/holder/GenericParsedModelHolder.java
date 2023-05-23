package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.MetaObject;
import cn.elphen.mytablehelper.api.Model;

/**
 * @author Elphen Liu
 * @since  0.0.1
 */

public class GenericParsedModelHolder<M extends Model> extends AbstractParsedModelHolder<M> {

    public GenericParsedModelHolder(M model, MetaObject metaObject) {
        super(model, metaObject);
    }

    public GenericParsedModelHolder(M model) {
        super(model);
    }
}
