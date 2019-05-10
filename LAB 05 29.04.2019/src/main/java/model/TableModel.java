package model;

public class TableModel {

    private int id;
    private String fieldName;
    private String fieldType;
    private String fieldValue;

    public TableModel(int id, String fieldName, String fieldType, String fielValue) {
        this.id = id;
        this.fieldName = fieldName;
        this.fieldType = fieldType;
        this.fieldValue = fielValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldType() {
        return fieldType;
    }

    public void setFieldType(String fieldType) {
        this.fieldType = fieldType;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
