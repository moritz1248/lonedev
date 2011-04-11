package sqlitejviewer;

public class DatabaseColumnDTO {
    private String columnName, defaultValue;
    private DatabaseDataType columnDataType;
    private boolean nullableColumn, primaryKey;


    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    /**
     * @return the columnDataType
     */
    public DatabaseDataType getColumnDataType() {
        return columnDataType;
    }

    /**
     * @param columnDataType the columnDataType to set
     */
    public void setColumnDataType(DatabaseDataType columnDataType) {
        this.columnDataType = columnDataType;
    }

    @Override
    public String toString() {
        return getColumnName() + " (" + getColumnDataType() + ")";
    }

    /**
     * @return the nullableColumn
     */
    public boolean isNullableColumn() {
        return nullableColumn;
    }

    /**
     * @param nullableColumn the nullableColumn to set
     */
    public void setNullableColumn(boolean nullableColumn) {
        this.nullableColumn = nullableColumn;
    }

    /**
     * @return the primaryKey
     */
    public boolean isPrimaryKey() {
        return primaryKey;
    }

    /**
     * @param primaryKey the primaryKey to set
     */
    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    /**
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }
}
