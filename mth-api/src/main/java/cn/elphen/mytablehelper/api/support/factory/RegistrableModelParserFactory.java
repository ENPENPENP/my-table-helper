package cn.elphen.mytablehelper.api.support.factory;

import cn.elphen.mytablehelper.api.ModelParser;
import cn.elphen.mytablehelper.api.ModelParserRegistrableFactory;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

import java.util.ArrayList;
import java.util.List;

/**
 * A registrable ModelParserFactory.
 *
 * @author Elphen Liu
 * @since 0.0.1
 */

public class RegistrableModelParserFactory
        extends AbstractModelParserFactory
        implements ModelParserRegistrableFactory<ModelParser<?>, RegistrableModelParserFactory> {

    public RegistrableModelParserFactory() {
        super();
    }

    @Override
    public RegistrableModelParserFactory register(ModelType modelType, ModelParser<?> modelParser) {
        synchronized (this.parsers) {
            List<ModelParser<?>> parserList = this.parsers.get(modelType);
            if (parserList == null) {
                parserList = new ArrayList<>();
            }
            parserList.add(modelParser);
            this.parsers.put(modelType, parserList);
        }
        return this;
    }

    @Override
    public RegistrableModelParserFactory unregister(ModelType modelType, ModelParser<?> instance) {
        synchronized (this.parsers) {
            List<ModelParser<?>> parserList = this.parsers.get(modelType);
            if (parserList != null) {
                parserList.remove(instance);
            }
        }
        return this;
    }

    @Override
    public RegistrableModelParserFactory clear() {
        synchronized (this.parsers) {
            this.parsers.clear();
        }
        return this;
    }

    @Override
    public void initialize() {

    }
}
