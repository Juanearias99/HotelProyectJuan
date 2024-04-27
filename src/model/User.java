package model;

public class User {

    private int id;
    private String username;
    private String email;
    private String password;
    private String contactDetails;
    private String rol;

    public User(int id, String username, String email, String password, String contactDetails) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.contactDetails = contactDetails;
        this.rol = rol;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
