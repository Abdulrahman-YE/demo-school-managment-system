package models;

import javafx.beans.property.*;
import utils.DateUtil;

import java.time.LocalDate;

public class Student {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty address;
    private StringProperty gender;
    private ObjectProperty<LocalDate> DOB;
    private StringProperty parentPhone;
    private IntegerProperty levelID;

    public Student() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.DOB = new SimpleObjectProperty<>();
        this.parentPhone = new SimpleStringProperty();
        this.levelID = new SimpleIntegerProperty();

    }

    public Student(int id, int levelID, String name, String address, String gender, String dateOfBirth, String parentPhoneNumber) {
        this(name, address, gender, dateOfBirth, parentPhoneNumber, levelID);
        this.id = new SimpleIntegerProperty(id);
    }

    public Student(String name, String address, String gender, String dateOfBirth, String parentPhoneNumber, int levelID) {
        this.name = new SimpleStringProperty(name);

        if (validateInput(gender)) {
            this.gender = new SimpleStringProperty(gender);
        } else {
            throw new IllegalArgumentException("Error : Gender can only be either male or female");
        }


        this.address = new SimpleStringProperty(address);

        if (DateUtil.validDate(dateOfBirth)) {
            this.DOB = new SimpleObjectProperty<>(DateUtil.parse(dateOfBirth));
        } else {
            throw new IllegalArgumentException("Error : Date of birth is not validate as a date");

        }

        this.parentPhone = new SimpleStringProperty(parentPhoneNumber);
        this.levelID = new SimpleIntegerProperty(levelID);
    }


    public Student(int id, String name, String address, String gender, LocalDate dateOfBirth, String parentNumber, int levelID) {

        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);


        if (validateInput(gender)) {
            this.gender = new SimpleStringProperty(gender);
        } else {
            throw new IllegalArgumentException("Error : Gender can only be either male or female");
        }
        this.address = new SimpleStringProperty(address);
        this.DOB = new SimpleObjectProperty<>(dateOfBirth);
        this.parentPhone = new SimpleStringProperty(parentNumber);
        this.levelID = new SimpleIntegerProperty(levelID);


    }


    private boolean validateInput(String gender) {
        String gen = gender.toLowerCase().trim();

        if (gen.equals("male") || gen.equals("female")) {
            return true;
        }
        return false;
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

    public String getGender() {
        return this.gender.get();
    }

    public void setGender(String gender) {
        if (validateInput(gender)) {
            this.gender.set(gender);
        } else {
            throw new IllegalArgumentException("Error : Gender can only be either male or female");
        }
    }

    public StringProperty getGenderProperty() {
        return this.gender;
    }

    public String getAddress() {
        return this.address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty getAddressProperty() {
        return this.address;
    }

    public LocalDate getDOB() {
        return this.DOB.get();
    }

    public void setDOB(LocalDate DOB) {
        this.DOB.set(DOB);
    }

    public ObjectProperty<LocalDate> getDOBProperty() {
        return this.DOB;
    }

    public String getParentPhone() {
        return this.parentPhone.get();
    }

    public void setParentPhone(String phone) {
        this.parentPhone.set(phone);
    }

    public StringProperty getParentPhoneProperty() {
        return this.parentPhone;
    }

    public Integer getLevelID() {
        return this.levelID.get();
    }

    public void setLevelID(Integer levelID) {
        this.levelID.set(levelID);
    }

    public IntegerProperty getLevelIDProperty() {
        return this.levelID;
    }

}
