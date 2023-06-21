package cn.elphen.mytablehelper.api.support.flow;

import cn.elphen.mytablehelper.api.*;
import cn.elphen.mytablehelper.api.exception.IllegalStateException;
import cn.elphen.mytablehelper.api.exception.InitializeFailedException;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * An abstracted implement of Flow interface.
 * @author Elphen
 * @since 0.0.1
 */
public abstract class AbstractFlow extends AbstractWrapper implements Flow {

    protected final Map<String, Object> context;
    protected DataSourceSupplier dataSourceSupplier;
    protected EntityHandler<?> entityHandler;
    protected EntityVerifier entityVerifier;
    protected ScriptGenerator scriptGenerator;
    protected ScriptExecutor scriptExecutor;
    private boolean initialized = false;

    public AbstractFlow() {
        this.context = new ConcurrentHashMap<>();
    }

    public boolean isInitialized() {
        return initialized;
    }

    @Override
    public void initialize() {
        if (entityHandler == null) {
            throw new InitializeFailedException("No ModelParserFactory found. " +
                    "Please provide a ModelParserFactory for dispatching parser.");
        }
        if (scriptGenerator == null) {
            throw new InitializeFailedException("No ScriptGenerator found. " +
                    "Please provide a ScriptGenerator to generate database operation script.");
        }
        if (scriptExecutor == null) {
            throw new InitializeFailedException("No ScriptExecutor Found." +
                    " Please provide a ScriptExecutor to execute database operation script.");
        }
        this.initialized = true;
    }

    @Override
    public void setDataSourceSupplier(DataSourceSupplier dataSourceSupplier) {
        this.dataSourceSupplier = dataSourceSupplier;
    }

    @Override
    public void setEntityHandler(EntityHandler<?> entityHandler) {
        this.entityHandler = entityHandler;
    }

    @Override
    public void setScriptGenerator(ScriptGenerator ddlScriptGenerator) {
        this.scriptGenerator = ddlScriptGenerator;
    }


    @Override
    public void setScriptExecutor(ScriptExecutor scriptExecutor) {
        this.scriptExecutor = scriptExecutor;
    }

    @Override
    public void setEntityVerifier(EntityVerifier entityVerifier) {
        this.entityVerifier = entityVerifier;
    }

    @Override
    public boolean process(Object source) {
        if (source == null) {
            throw new IllegalArgumentException("Given source object is null.");
        }
        // check the flow state
        checkInitializeState();

        // handle the source object
        Entity entity = handle(source);

        //verify entity
        verifyEntity(entity);

        // generate scriptBlock by entity
        ScriptBlock<?> scriptBlock = buildScript(entity);

        // System.out.println(scriptBlock);

        // execute the scriptBlock
        return executeScript(scriptBlock);
    }

    @Override
    public void setContext(Map<String, Object> context) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        }
        this.context.clear();
        if (!context.isEmpty()) {
            this.context.putAll(context);
        }
    }

    @Override
    public Map<String, Object> getCopyOfContext() {
        return new ConcurrentHashMap<>(this.context);
    }

    @Override
    public void setConfig(String option, Object value) {
        if (Objects.isNull(option) || "".equalsIgnoreCase(option.trim()) || Objects.isNull(value)) {
            return;
        }
        synchronized (this.context) {
            this.context.put(option, value);
        }
    }

    @Override
    public void unsetConfig(String option) {
        if (Objects.isNull(option) || "".equalsIgnoreCase(option.trim())) {
            return;
        }
        synchronized (this.context) {
            this.context.remove(option);
        }
    }

    /**
     * Extract and parser an object, convert to an entity model.
     *
     * @param source source object
     * @return parsed model holder
     */
    protected Entity handle(Object source) {
        return this.entityHandler.handle(source);
    }

    /**
     * Build a database create table script from a parsed model.
     *
     * @param entity entity model
     * @return database create table script
     */
    protected ScriptBlock<?> buildScript(Entity entity) {
        return this.scriptGenerator.generateScript(entity);
    }

    /**
     * Execute database create table script.
     *
     * @param scriptBlock database create table script
     * @return execute result
     */
    protected boolean executeScript(ScriptBlock<?> scriptBlock) {
        if (this.dataSourceSupplier == null || dataSourceSupplier.get() == null) {
            throw new InitializeFailedException("Can not get connect from given supplier, " +
                    "may be the supplier is null or it return nothing from 'get()' method.");
        }

        if (scriptBlock == null || scriptBlock.count() == 0) {
            throw new IllegalArgumentException("Given ScriptBlock must contain one script as least.");
        }

        return scriptExecutor.executeWith(scriptBlock, dataSourceSupplier.testAndGet());
    }

    protected void verifyEntity(Entity entity) {
        if (this.entityVerifier != null) {
            entityVerifier.doVerify(entity);
        }
    }

    private void checkInitializeState() {
        if (!this.initialized) {
            throw new IllegalStateException("Flow is un-initialized. Please invoke method initialize() to initialize.");
        }
    }
}
