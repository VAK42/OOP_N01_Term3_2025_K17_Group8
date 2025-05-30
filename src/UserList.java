import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserList {
  private final List<User> users = new ArrayList<>();

  public void add(User user) {
    users.add(user);
  }

  public boolean edit(UUID userId, String newUsername, String newPassword, String newEmail, String newRole) {
    for (User u : users) {
      if (u.getUserId().equals(userId)) {
        u.setUsername(newUsername);
        u.setPassword(newPassword);
        u.setEmail(newEmail);
        u.setRole(newRole);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID userId) {
    return users.removeIf(u -> u.getUserId().equals(userId));
  }

  public List<User> getAll() {
    return new ArrayList<>(users);
  }
}