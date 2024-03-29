/*
 * Data.java
 */
package base.framework.logincontroler;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Data component for Login form
 * @author Franky Laseure
 */
public class Data {
    
    private final StringProperty username = new SimpleStringProperty();
    public String getUsername() { return username.get(); }
    public void setUsername(String newValue) { username.set(newValue); }
    public StringProperty usernameProperty() { return username; }    
    
    private final StringProperty password = new SimpleStringProperty();
    public String getPassword() { return password.get(); }
    public void setPassword(String newValue) { password.set(newValue); }
    public StringProperty passwordProperty() { return password; }    
    
    public Data() {
    }
    
}
