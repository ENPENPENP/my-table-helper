package cn.elphen.mytablehelper.api.support.constant;

/**
 * Type of model. Used to distinguish the model is which part of an Entity.
 *
 * @author Elphen Liu
 * @since 0.0.1
 */
public enum ModelType {
    /**
     * The highest level model, it must include all below required level model, beside optional level.
     */
    ENTITY,
    /**
     * Required level.
     * Database level model, storage the main information about database.
     */
    SCHEMA,
    /**
     * Required level.
     * Table level model, storage the main information about table.
     */
    TABLE,
    /**
     * Optional level.
     * Column level model, storage the main information about column.
     */
    COLUMN,
    /**
     * Optional level.
     * Index level model, storage the main information about index.
     */
    INDEX,
    /**
     * Optional level.
     * Key level model, storage the main information about key.
     */
    KEY
}
