import java.util.UUID;

public class ReportTest {
  public static void runTest() {
    UUID reportId = UUID.randomUUID();
    User user = new User(UUID.randomUUID(), "reportuser", "reportpass", "reportuser@example.com", "manager");
    Report report = new Report(reportId, user, "Monthly Inventory Report", "Summary Of Stock Levels And Movements For The Month");
    System.out.println("Report ID: " + report.getReportid());
    System.out.println("Report Name: " + report.getRpname());
    System.out.println("Report Info: " + report.getRpinfo());
    System.out.println("User: " + report.getUser().getUsername());
    System.out.println("Email: " + report.getUser().getEmail());
    System.out.println("Role: " + report.getUser().getRole());
  }
}