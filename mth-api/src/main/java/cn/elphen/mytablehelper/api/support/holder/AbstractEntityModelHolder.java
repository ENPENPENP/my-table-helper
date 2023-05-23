package cn.elphen.mytablehelper.api.support.holder;

import cn.elphen.mytablehelper.api.support.AbstractWrapper;
import cn.elphen.mytablehelper.api.MetaObject;
import cn.elphen.mytablehelper.api.Model;
import cn.elphen.mytablehelper.api.ParsedModelHolder;
import cn.elphen.mytablehelper.api.support.constant.ModelType;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Elphen
 * @since 0.0.1
 */
public abstract class AbstractEntityModelHolder<T extends Model> extends AbstractWrapper implements ParsedModelHolder<T> {

    private ParsedModelHolder<?> database;
    private final ParsedModelHolder<T> table;
    private final List<ParsedModelHolder<?>> columns = new ArrayList<>();
    private final List<ParsedModelHolder<?>> indexes = new ArrayList<>();
    private final List<ParsedModelHolder<?>> keys = new ArrayList<>();

    public AbstractEntityModelHolder(ParsedModelHolder<T> table) {
        if (table == null) {
            throw new IllegalArgumentException("Given table model must not null.");
        }
        this.table = table;
    }

    public ParsedModelHolder<?> getDatabase() {
        return database;
    }

    public void setDatabase(ParsedModelHolder<?> database) {
        this.database = database;
    }

    public ParsedModelHolder<?> getTable() {
        return table;
    }

    public List<ParsedModelHolder<?>> getColumns() {
        return columns;
    }

    public List<ParsedModelHolder<?>> getIndexes() {
        return indexes;
    }

    public List<ParsedModelHolder<?>> getKeys() {
        return keys;
    }

    public AbstractEntityModelHolder<T> addColumn(ParsedModelHolder<?> columnModel){
        this.columns.add(columnModel);
        return this;
    }

    public AbstractEntityModelHolder<T> addColumns(List<ParsedModelHolder<?>> columnModels) {
        this.columns.addAll(columnModels);
        return this;
    }

    public AbstractEntityModelHolder<T> addIndex(ParsedModelHolder<?> indexModel) {
        this.indexes.add(indexModel);
        return this;
    }

    public AbstractEntityModelHolder<T> addIndexes(List<ParsedModelHolder<?>> indexModels) {
        this.indexes.addAll(indexModels);
        return this;
    }

    public AbstractEntityModelHolder<T> addKey(ParsedModelHolder<?> keyModel) {
        this.keys.add(keyModel);
        return this;
    }

    public AbstractEntityModelHolder<T> addKeys(List<ParsedModelHolder<?>> keyModels){
        this.keys.addAll(keyModels);
        return this;
    }

    @Override
    public T getModel() {
        return this.table.getModel();
    }

    @Override
    public ModelType getType() {
        return this.table.getType();
    }

    @Override
    public MetaObject getMetaObject() {
        return this.table.getMetaObject();
    }
}
