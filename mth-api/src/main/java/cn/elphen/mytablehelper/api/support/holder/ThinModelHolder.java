package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.Model;

import java.util.List;

/**
 * @author Elphen
 * @since 0.0.1
 */
public class ThinModelHolder<M extends Model> {

    private Model database;
    private final M tableModel;
    private final List<Model> columnModels;
    private final List<Model> indexModels;
    private final List<Model> keyModels;

    public ThinModelHolder(M tableModel, List<Model> columnModels, List<Model> indexModels, List<Model> keyModels) {
        this.tableModel = tableModel;
        this.columnModels = columnModels;
        this.indexModels = indexModels;
        this.keyModels = keyModels;
    }

    public ThinModelHolder(Model database, M tableModel, List<Model> columnModels, List<Model> indexModels, List<Model> keyModels) {
        this.database = database;
        this.tableModel = tableModel;
        this.columnModels = columnModels;
        this.indexModels = indexModels;
        this.keyModels = keyModels;
    }

    public Model getDatabase() {
        return database;
    }

    public M getTableModel() {
        return tableModel;
    }

    public List<Model> getColumnModels() {
        return columnModels;
    }

    public List<Model> getIndexModels() {
        return indexModels;
    }

    public List<Model> getKeyModels() {
        return keyModels;
    }
}
