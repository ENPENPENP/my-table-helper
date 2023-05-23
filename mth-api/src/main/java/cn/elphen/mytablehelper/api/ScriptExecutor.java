package cn.elphen.mytablehelper.api;

import javax.sql.DataSource;

/**
 * A executor to execute a database script with specified datasource.
 * <p>
 *     Use <code>DataSourceSupplier</code> for convenient to provide a datasource.
 * </p>
 *
 * @author Elphen
 * @see DataSourceSupplier
 * @since 0.0.1
 */
public interface ScriptExecutor {

    /**
     * Execute the script with a datasource.
     *
     * @param script     script
     * @param dataSource datasource for a database
     * @see DataSource
     * @return execute result
     */
    boolean executeWith(String script, DataSource dataSource);

}
