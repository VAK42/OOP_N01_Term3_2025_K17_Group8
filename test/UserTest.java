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

  public static void testAdd() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "bob", "bobSecure!", "bob@example.com", "staff");
    userList.add(user);

    System.out.println("\n[testAdd] User list after adding:");
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId() + ", Username: " + u.getUsername());
    }
  }

  public static void testEdit() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "charlie", "charlie123", "charlie@example.com", "viewer");

    UUID userId = user.getUserId();
    userList.add(user);

    boolean result = userList.edit(userId, "charlieNew", "newPass456", "charlie.new@example.com", "editor");

    System.out.println("\n[testEdit] Edit result: " + result);
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId() + ", Username: " + u.getUsername() + ", Email: " + u.getEmail() + ", Role: " + u.getRole());
    }
  }

  public static void testDelete() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "diana", "dianaPwd!", "diana@example.com", "manager");

    UUID userId = user.getUserId();
    userList.add(user);

    boolean deleted = userList.delete(userId);

    System.out.println("\n[testDelete] Delete result: " + deleted);
    System.out.println("Remaining users:");
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId() + ", Username: " + u.getUsername());
    }
  }
}
