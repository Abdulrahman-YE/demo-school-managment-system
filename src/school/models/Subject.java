package school.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Subject {
    private IntegerProperty id;
    private StringProperty name;
    private IntegerProperty levelID;
    private IntegerProperty teacherID;

    public Subject() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.levelID = new SimpleIntegerProperty();
        this.teacherID = new SimpleIntegerProperty();
    }

    public Subject(int id, String name, int levelID, int teacherID) {
        this(name, levelID, teacherID);
        this.id = new SimpleIntegerProperty(id);

    }

    public Subject(String name, int levelID, int teacherID) {

        this.name = new SimpleStringProperty(name);
        this.levelID = new SimpleIntegerProperty(levelID);
        this.teacherID = new SimpleIntegerProperty(teacherID);
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

    public Integer getLevelID() {
        return this.levelID.get();
    }

    public void setLevelID(Integer LEVE) {
        this.levelID.set(LEVE);
    }

    public IntegerProperty getLevelIDProperty() {
        return this.levelID;
    }

    public Integer getTeacherID() {
        return this.teacherID.get();
    }

    public void setTeacherID(Integer teacherID) {
        this.teacherID.set(teacherID);
    }

    public IntegerProperty getTeacherIDProperty() {
        return this.teacherID;
    }
}
