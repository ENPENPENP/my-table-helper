package cn.elphen.mytablehelper.api;

import javax.sql.DataSource;

/**
 * A executor to execute a database script with specified datasource.
 * <p>
 * Use <code>DataSourceSupplier</code> for convenient to provide a datasource.
 * </p>
 *
 * @author Elphen
 * @see DataSourceSupplier
 * @since 0.0.1
 */
public interface ScriptExecutor {

    /**
     * Execute the scriptBlock with a datasource.
     *
     * @param scriptBlock scriptBlock
     * @param dataSource  datasource for a database
     * @return execute result
     * @see DataSource
     */
    boolean executeWith(ScriptBlock<?> scriptBlock, DataSource dataSource);

}
