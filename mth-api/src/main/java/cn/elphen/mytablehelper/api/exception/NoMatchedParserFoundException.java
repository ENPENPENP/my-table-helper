package cn.elphen.mytablehelper.api.exception;

import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class NoMatchedParserFoundException extends RuntimeException {

    public NoMatchedParserFoundException(ModelType modelType) {
        super(String.format("No matched parser found for model type '%s'", modelType.name()));
    }

    public NoMatchedParserFoundException(Class<?> modelClass) {
        super(String.format("No matched parser found for type '%s'", modelClass.getName()));
    }
}
