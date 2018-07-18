package vn.com.phatngoit.restaurants2.Login;

/**
 * Created by HV on 6/13/2018.
 */

public class UserClass {

    public String email, password, fullName, phoneNumber;

    public UserClass(String email, String password, String fullName, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
    }

    public UserClass() {
    }

    public UserClass(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
