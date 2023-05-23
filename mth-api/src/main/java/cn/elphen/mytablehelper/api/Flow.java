package cn.elphen.mytablehelper.api;

import cn.elphen.mytablehelper.api.exception.InvalidEntityException;

/**
 * Flow provided a simple way to execute a completed operation logic from parse object to operate database.
 * It must set some component to implement parts function of the flow.
 *
 * @author Elphen Liu
 * @see DataSourceSupplier
 * @see EntityHandler
 * @see ScriptGenerator
 * @see ScriptExecutor
 * @since 0.0.1
 */
public interface Flow extends Configurable, Wrapper {

    /**
     * Do some operation to initialize the flow
     * and check the required component state: ModelParserFactory, ScriptGenerator and ScriptExecutor.
     * Strongly suggest that do the operation that inject or set object to any field of implement class in this method
     * (if they are not declared with <code>final</code>).
     */
    void initialize();

    /**
     * Set a supplier which can provide a datasource.
     *
     * @param dataSourceSupplier supplier for datasource
     * @see DataSourceSupplier
     */
    void setDataSourceSupplier(DataSourceSupplier dataSourceSupplier);

    /**
     * Set a entity handler.
     *
     * @param entityHandler entity handler
     * @see EntityHandler
     */
    void setEntityHandler(EntityHandler<?> entityHandler);

    /**
     * Set a database DDL script generator.
     *
     * @param ddlScriptGenerator database DDL script generator
     * @see ScriptGenerator
     */
    void setScriptGenerator(ScriptGenerator ddlScriptGenerator);

    /**
     * Set a database DDL script executor
     *
     * @param scriptExecutor database DDL script executor
     * @see ScriptExecutor
     */
    void setScriptExecutor(ScriptExecutor scriptExecutor);

    /**
     * Set a custom entity verifier.
     *
     * @param entityVerifier entity verifier
     */
    void setEntityVerifier(EntityVerifier entityVerifier);

    /**
     * Execute a flow with sequential sequence including parse object, build database operation script
     * and execute script.
     * <p>
     * Required an available <code>DataSourceSupplier</code> set by
     * {@link Flow#setDataSourceSupplier(DataSourceSupplier)} before invoking this method.
     * </p>
     *
     * @param source source object to be parsed
     * @return the final result at executing script operation
     */
    boolean process(Object source);

}
