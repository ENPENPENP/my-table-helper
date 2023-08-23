package cn.elphen.mytablehelper.api.support.factory;

import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.ModelParser;
import cn.elphen.mytablehelper.api.ModelParserFactory;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import cn.elphen.mytablehelper.api.support.constant.ModelType;
import cn.elphen.mytablehelper.api.util.PrintUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * An abstracted implement of ModelParserFactory.
 *
 * @author Elphen Liu
 * @since 0.0.1
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
        List<ModelParser<?>> modelParsers = this.parsers.get(modelType);
        if (modelParsers.isEmpty()) {
            throw new NoMatchedParserFoundException(modelType);
        }
        if (modelParsers.size() > 1) {
            PrintUtil.printMsgLn(String.format("Warn: found %s matched ModelParser for ModelType '%s'.",
                    modelParsers.size(), modelType));
        }
        return modelParsers.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Model> ModelParser<T> getParser(Class<T> modelClass) {
        List<ModelParser<?>> modelParsers = this.parsers.values()
                .stream()
                .flatMap(Collection::stream)
                .filter(parser -> parser.isSupport(modelClass))
                .collect(Collectors.toList());

        if (modelParsers.isEmpty()) {
            throw new NoMatchedParserFoundException(modelClass);
        }
        if (modelParsers.size() > 1) {
            PrintUtil.printMsgLn(String.format("Warn: found %s matched ModelParser for type '%s'.",
                    modelParsers.size(), modelClass));
        }
        return (ModelParser<T>) modelParsers.get(0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends Model> ModelParser<T> getParser(ModelType modelType, Class<T> modelClass) {
        List<ModelParser<?>> modelParsers = this.parsers.get(modelType)
                .stream()
                .filter(parser -> parser.isSupport(modelClass))
                .collect(Collectors.toList());

        if (modelParsers.isEmpty()) {
            throw new NoMatchedParserFoundException(modelClass);
        }
        if (modelParsers.size() > 1) {
            PrintUtil.printMsgLn(String.format("Warn: found %s matched ModelParser for type '%s'.",
                    modelParsers.size(), modelClass));
        }
        return (ModelParser<T>) modelParsers.get(0);
    }
}
