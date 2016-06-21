package ge.freeuni.quizwebsite.model;

/**
 * Created by Saba on 6/14/2016.
 */
public class Account {
    
    private Integer id;
    private String username;
    private String hashedPassword;
    private String email;
    private String firstName;
    private String lastName;

    public Account(String username, String hashedPassword, String email, String firstName, String lastName) {
        this(null, username, hashedPassword, email, firstName, lastName);
    }

    public Account(Integer id, String username, String hashedPassword, String email, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.hashedPassword = hashedPassword;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "ID: " + id + "; Username: " + username + "; "
                + "Hashed Password: " + hashedPassword + "; "
                + "E-mail: " + email + "; "
                + "First Name: " + firstName + "; "
                + "Last Name: " + lastName + ";";
    }

}
