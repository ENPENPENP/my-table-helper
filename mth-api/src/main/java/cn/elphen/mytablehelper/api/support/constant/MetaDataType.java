package cn.elphen.mytablehelper.api.support.constant;

/**
 * Type of the source object. Used to distinguish the object is a {@link Class} object
 * or a {@link java.lang.reflect.Field} or other unknown type instance.
 *
 * @author Elphen
 * @since 0.0.1
 */
public enum MetaDataType {
    /**
     * The object is a <code>Class</code> object.
     */
    OBJECT_TYPE,
    /**
     * The object is a <code>Field</code> object.
     */
    TYPE_FIELD,
    /**
     * Normal instantiated object.
     */
    OBJECT;
}
