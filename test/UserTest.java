import java.util.UUID;

public class TestUser {

    public static void test() {
        System.out.println("=== TestUser ===");

        UUID userId = UUID.randomUUID();
        UserEntity user = new UserEntity(userId, "testuser", "password123", "testuser@example.com", "user");

        System.out.println("UserId: " + user.getUserId());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Password: " + user.getPassword());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Role: " + user.getRole());

        user.setUsername("updateduser");
        user.setPassword("newpassword");
        user.setEmail("updateduser@example.com");
        user.setRole("admin");

        System.out.println("Updated Username: " + user.getUsername());
        System.out.println("Updated Password: " + user.getPassword());
        System.out.println("Updated Email: " + user.getEmail());
        System.out.println("Updated Role: " + user.getRole());

        System.out.println("=== End TestUser ===");
    }
}