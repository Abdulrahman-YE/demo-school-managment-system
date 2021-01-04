package models;

import javafx.beans.property.*;
import utils.DateUtil;

import java.time.LocalDate;

public class Teacher {

    private IntegerProperty id;
    private StringProperty name;
    private StringProperty gender;
    private StringProperty address;
    private ObjectProperty<LocalDate> DOB;
    private StringProperty phone;
    private IntegerProperty salary;

    public Teacher() {
        this.id = new SimpleIntegerProperty();
        this.name = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.DOB = new SimpleObjectProperty<LocalDate>();
        this.phone = new SimpleStringProperty();
        this.salary = new SimpleIntegerProperty();


    }


    public Teacher(int id, String name, String gender, String address, LocalDate DOB, String phonenumber, Integer salary) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);

        if (validateInput(gender)) {
            this.gender = new SimpleStringProperty(gender);
        } else {
            throw new IllegalArgumentException("Error : Gender can only be either male or female");
        }
        this.address = new SimpleStringProperty(address);
        this.DOB = new SimpleObjectProperty<LocalDate>(DOB);
        this.phone = new SimpleStringProperty(phonenumber);
        this.salary = new SimpleIntegerProperty(salary);
    }

    public Teacher(int id, String name, String gender, String address, String DOB, String phonenumber, Integer salary) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);

        if (validateInput(gender)) {
            this.gender = new SimpleStringProperty(gender);
        } else {
            throw new IllegalArgumentException("Error : Gender can only be either male or female");
        }
        this.address = new SimpleStringProperty(address);
        if (DateUtil.validDate(DOB)) {
            this.DOB = new SimpleObjectProperty<LocalDate>(DateUtil.parse(DOB));
        } else {
            throw new IllegalArgumentException("Error : Date of birth is not validate as a date");

        }

        this.phone = new SimpleStringProperty(phonenumber);
        this.salary = new SimpleIntegerProperty(salary);
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

    public String getPhone() {
        return this.phone.get();
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public StringProperty getPhoneProperty() {
        return this.phone;
    }

    public Integer getSalary() {
        return this.salary.get();
    }

    public void setSalary(Integer salary) {
        this.salary.set(salary);
    }

    public IntegerProperty getSalaryProperty() {
        return this.salary;
    }
}
