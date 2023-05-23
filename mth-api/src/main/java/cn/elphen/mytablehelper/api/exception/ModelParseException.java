package cn.elphen.mytablehelper.api.exception;

import cn.elphen.mytablehelper.api.support.constant.ModelType;

/**
 * @author Elphen
 * @since  0.0.1
 */

public class ModelParseException extends RuntimeException {

    private final ModelType modelType;

    public ModelParseException(String message, ModelType type) {
        super(message);
        this.modelType = type;
    }

    public ModelParseException(String message, Throwable cause, ModelType modelType) {
        super(message, cause);
        this.modelType = modelType;
    }

    public ModelParseException(Throwable cause, ModelType modelType) {
        super(cause);
        this.modelType = modelType;
    }

    public ModelType getType() {
        return modelType;
    }
}
