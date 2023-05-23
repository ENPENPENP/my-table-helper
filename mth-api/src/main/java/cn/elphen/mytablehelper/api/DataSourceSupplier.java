package cn.elphen.mytablehelper.api;

import javax.sql.DataSource;

/**
 * Provide a container to get a datasource.
 *
 * @author Elphen
 * @since 0.0.1
 */
public interface DataSourceSupplier {

    /**
     * Gets a DataSource
     *
     * @return a DataSource
     */
    DataSource get();


    /**
     * Gets a DataSource and test.
     *
     * @return a tested DataSource
     */
    DataSource testAndGet();

}
