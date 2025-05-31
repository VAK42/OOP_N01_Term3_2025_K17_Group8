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

  public static void testAdd() {
    ReportList reportList = new ReportList();
    User user = new User(UUID.randomUUID(), "alice", "alice123", "alice@example.com", "admin");
    Report report = new Report(UUID.randomUUID(), user, "Weekly Sales Report", "Analysis of weekly sales performance");

    reportList.add(report);

    System.out.println("\n[testAdd] Report list after adding:");
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid() + ", Name: " + r.getRpname());
    }
  }

  public static void testEdit() {
    ReportList reportList = new ReportList();
    User originalUser = new User(UUID.randomUUID(), "bob", "passbob", "bob@example.com", "staff");
    Report report = new Report(UUID.randomUUID(), originalUser, "Stock Alert", "Low stock warning for several products");

    UUID reportId = report.getReportid();
    reportList.add(report);

    User updatedUser = new User(UUID.randomUUID(), "charlie", "charliepass", "charlie@example.com", "manager");
    boolean result = reportList.edit(reportId, updatedUser, "Updated Stock Alert", "Updated info with restock details");

    System.out.println("\n[testEdit] Edit result: " + result);
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid() + ", Name: " + r.getRpname() + ", User: " + r.getUser().getUsername());
    }
  }

  public static void testDelete() {
    ReportList reportList = new ReportList();
    User user = new User(UUID.randomUUID(), "david", "davidpass", "david@example.com", "analyst");
    Report report = new Report(UUID.randomUUID(), user, "Daily Report", "Auto-generated daily summary");

    UUID reportId = report.getReportid();
    reportList.add(report);

    boolean deleted = reportList.delete(reportId);

    System.out.println("\n[testDelete] Delete result: " + deleted);
    System.out.println("Remaining reports:");
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid() + ", Name: " + r.getRpname());
    }
  }
}
