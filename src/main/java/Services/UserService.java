package Services;

import DAO.UserDAO;
import Models.User;

import java.sql.SQLException;

public class UserService {
    private final UserDAO userDAO;

    public UserService() throws SQLException {
        this.userDAO = new UserDAO();
    }

    public User login(String username, String password) {
        try {
            User user = userDAO.getUsername(username);

            if (user == null) {
                System.out.println("❌ User not found!");
                return null;
            }

            if (user.getPassword().equals(password)) {
                System.out.println("✅ Login successful! Welcome, " + user.getUsername());
                System.out.println("🔹 Role: " + user.getRole());
                return user;
            } else {
                System.out.println("❌ Invalid password!");
                return null;
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error during login: " + e.getMessage());
            return null;
        }
    }
}
