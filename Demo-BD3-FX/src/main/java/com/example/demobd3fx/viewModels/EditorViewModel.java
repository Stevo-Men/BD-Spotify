package com.example.demobd3fx.viewModels;

import com.example.demobd3fx.models.Editor;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EditorViewModel {
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty id = new SimpleIntegerProperty();
    public EditorViewModel(Editor editor) {
        setName(editor.getName());
        setId(editor.getId());
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }
}
