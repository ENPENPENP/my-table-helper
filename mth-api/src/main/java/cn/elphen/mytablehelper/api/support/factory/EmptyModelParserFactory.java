package cn.elphen.mytablehelper.api.support.factory;

import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.ModelParser;
import cn.elphen.mytablehelper.api.ModelParserFactory;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * @author Elphen
 * @since  0.0.1
 */
public class EmptyModelParserFactory implements ModelParserFactory {

    @Override
    public void initialize() {

    }

    @Override
    public <T extends Model> ModelParser<T> getParser(Class<T> modelClass) {
        throw new NoMatchedParserFoundException(modelClass);
    }

    @Override
    public ModelParser<?> getParser(ModelType modelType) {
        throw new NoMatchedParserFoundException(modelType);
    }

    @Override
    public <T extends Model> ModelParser<T> getParser(ModelType modelType, Class<T> modelClass) {
        throw new NoMatchedParserFoundException(modelClass);
    }
}
