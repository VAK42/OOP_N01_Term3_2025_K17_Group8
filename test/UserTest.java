import java.util.UUID;

public class UserTest {
  public static void runTest() {
    UUID userId = UUID.randomUUID();
    User user = new User(userId, "vak", "barcelona", "vak@barca.com", "admin");
    System.out.println("User ID: " + user.getUserId());
    System.out.println("Username: " + user.getUsername());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Email: " + user.getEmail());
    System.out.println("Role: " + user.getRole());
  }

  public static void testAdd() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "lewy", "12345678", "lewy@barca.com", "staff");
    userList.add(user);
    System.out.println("User Add: ");
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId());
      System.out.println("Username: " + u.getUsername());
    }
  }

  public static void testEdit() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "pedri", "87654321", "pedri@barca.com", "viewer");
    UUID userId = user.getUserId();
    userList.add(user);
    boolean result = userList.edit(userId, "gavi", "13572468", "gavi@barca.com", "editor");
    System.out.println("User Edit: " + result);
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId());
      System.out.println("Username: " + u.getUsername());
      System.out.println("Email: " + u.getEmail());
      System.out.println("Role: " + u.getRole());
    }
  }

  public static void testDelete() {
    UserList userList = new UserList();
    User user = new User(UUID.randomUUID(), "dejong", "24681357", "dejong@barca.com", "manager");
    UUID userId = user.getUserId();
    userList.add(user);
    boolean deleted = userList.delete(userId);
    System.out.println("User Delete: " + deleted);
    for (User u : userList.getAll()) {
      System.out.println("User ID: " + u.getUserId());
      System.out.println("Username: " + u.getUsername());
    }
  }
}