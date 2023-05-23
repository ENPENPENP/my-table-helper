package cn.elphen.mytablehelper.api.support.factory;

import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.ModelParser;
import cn.elphen.mytablehelper.api.ModelParserFactory;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstracted implement of ModelParserFactory.
 *
 * @author Elphen Liu
 * @since  0.0.1
 */

public abstract class AbstractModelParserFactory implements ModelParserFactory {

    protected final Map<ModelType, List<ModelParser<?>>> parsers;

    public AbstractModelParserFactory(Map<ModelType, List<ModelParser<?>>> parsers) {
        this.parsers = parsers;
    }

    public AbstractModelParserFactory() {
        this.parsers = new ConcurrentHashMap<>();
    }

    @Override
    public ModelParser<?> getParser(ModelType modelType) {
        List<ModelParser<?>> parserList = this.parsers.get(modelType);
        if (!parserList.isEmpty()) {
            return parserList.get(0);
        }
        throw new NoMatchedParserFoundException(modelType);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Model> ModelParser<T> getParser(Class<T> modelClass) {
        return (ModelParser<T>) this.parsers.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(parser -> parser.isSupport(modelClass))
                .findFirst()
                .orElseThrow(() -> new NoMatchedParserFoundException(modelClass));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Model> ModelParser<T> getParser(ModelType modelType, Class<T> modelClass) {
        List<ModelParser<?>> parserList = this.parsers.get(modelType);
        if (!parserList.isEmpty()) {
            return (ModelParser<T>) parserList
                    .stream()
                    .filter(parser -> parser.isSupport(modelClass))
                    .findFirst()
                    .orElseThrow(() -> new NoMatchedParserFoundException(modelClass));
        }
        throw new NoMatchedParserFoundException(modelClass);
    }
}
