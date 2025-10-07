package Models;

public class user {
    int id;
    String username;
    String password;
    String role;

    public user() {
    }

    public user(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
