package cn.elphen.mytablehelper.api.support.handler;

import cn.elphen.mytablehelper.api.*;
import cn.elphen.mytablehelper.api.exception.NoMatchedParserFoundException;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

/**
 * A universal ModelHandler enhanced with ModelParserFactory.
 * @author Elphen
 * @since 0.0.1
 */
public class AutoHandler {

    private boolean ignoreNoMatchedParserFound = false;

    private final ModelParserFactory modelParserFactory;

    public AutoHandler(ModelParserFactory modelParserFactory) {
        this.modelParserFactory = modelParserFactory;
    }

    public <M extends Model>  M handle(final Class<M> modelClass, final MetaObject metaObject) {
        try {
            ModelParser<M> modelParser = this.modelParserFactory.getParser(modelClass);
            return modelParser.parse(metaObject);
        } catch (NoMatchedParserFoundException noMatchedParserFoundException) {
            if (!ignoreNoMatchedParserFound){
                throw noMatchedParserFoundException;
            }
        }
        return null;
    }

    public <M extends Model> List<M> handle(final Class<M> modelClass, MetaObject... metaObject) {
        List<M> models = new ArrayList<>();

        try {
            ModelParser<M> parser = this.modelParserFactory.getParser(modelClass);

            for (MetaObject object : metaObject) {
                models.add(parser.parse(object));
            }
        } catch (NoMatchedParserFoundException noMatchedParserFoundException) {
            if (!ignoreNoMatchedParserFound) {
                throw noMatchedParserFoundException;
            }
        }

        return models;
    }

    public boolean isIgnoreNoMatchedParserFound() {
        return ignoreNoMatchedParserFound;
    }

    public void setIgnoreNoMatchedParserFound(boolean ignoreNoMatchedParserFound) {
        this.ignoreNoMatchedParserFound = ignoreNoMatchedParserFound;
    }
}
