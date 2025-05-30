import java.util.UUID;

public class UserTest {
  public static void runTest() {
    UUID userId = UUID.randomUUID();
    User user = new User(userId, "alice", "alicePass123", "alice@example.com", "admin");
    System.out.println("User ID: " + user.getUserId());
    System.out.println("Username: " + user.getUsername());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Role: " + user.getRole());
  }
}