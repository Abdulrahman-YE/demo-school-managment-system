package school.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Account {
    private IntegerProperty id;
    private StringProperty username;
    private StringProperty password;

    public Account() {
        this.id = new SimpleIntegerProperty();
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }

    public Account(String username, String password) {
        this();
        this.username.set(username);
        this.password.set(password);
    }

    public Integer getID()
    {
        return this.id.get();
    }

    public void setID(Integer id)
    {
        this.id.set(id);
    }

    public IntegerProperty getIDProperty()
    {
        return this.id;
    }

    public String getUsername()
    {
        return this.username.get();
    }

    public void setUsername(String username)
    {
        this.username.set(username);
    }

    public StringProperty getUsernameProperty()
    {
        return this.username;
    }


    public String getPassword()
    {
        return this.password.get();
    }

    public void setPassword(String password)
    {
        this.password.set(password);
    }

    public StringProperty getPasswordProperty()
    {
        return this.password;
    }
}
