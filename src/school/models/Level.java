package school.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Level {

    private final IntegerProperty id;
    private StringProperty name;

    public Level() {
        this(null);

    }

    public Level(String name) {
        this.id = new SimpleIntegerProperty(0);
        this.name = new SimpleStringProperty(name);
        this.name.set(name);
    }

    public Level(Integer id, String name) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
    }

    public Integer getID() {
        return this.id.get();
    }

    public void setID(Integer id) {
        this.id.set(id);
    }

    public IntegerProperty getIDProperty() {
        return this.id;
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProperty() {
        return this.name;
    }
}
