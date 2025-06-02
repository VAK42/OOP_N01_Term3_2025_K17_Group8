import java.util.UUID;

public class ReportTest {
  public static void runTest() {
    UUID reportId = UUID.randomUUID();
    User user = new User(UUID.randomUUID(), "xavi", "pwd", "xavi@barca.com", "manager");
    Report report = new Report(reportId, user, "Monthly Inventory Report",
        "Summary Of Stock Levels & Movements For The Month");
    System.out.println("Report ID: " + report.getReportid());
    System.out.println("Report Name: " + report.getRpname());
    System.out.println("Report Info: " + report.getRpinfo());
    System.out.println("User: " + report.getUser().getUsername());
    System.out.println("Email: " + report.getUser().getEmail());
    System.out.println("Role: " + report.getUser().getRole());
  }

  public static void testAdd() {
    ReportList reportList = new ReportList();
    User user = new User(UUID.randomUUID(), "flick", "pwd", "flick@barca.com", "admin");
    Report report = new Report(UUID.randomUUID(), user, "Weekly Sales Report", "Analysis Of Weekly Sales Performance");
    reportList.add(report);
    System.out.println("Report Add: ");
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid());
      System.out.println("Name: " + r.getRpname());
    }
  }

  public static void testEdit() {
    ReportList reportList = new ReportList();
    User originalUser = new User(UUID.randomUUID(), "enrique", "xana", "enrique@barca.com", "staff");
    Report report = new Report(UUID.randomUUID(), originalUser, "Stock Alert",
        "Low Stock Warning For Several Products");
    UUID reportId = report.getReportid();
    reportList.add(report);
    User updatedUser = new User(UUID.randomUUID(), "pique", "shakira", "pique@barca.com", "manager");
    boolean result = reportList.edit(reportId, updatedUser, "Updated Stock Alert", "Updated Info With Restock Details");
    System.out.println("Report Edit: " + result);
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid());
      System.out.println("Name: " + r.getRpname());
      System.out.println("User: " + r.getUser().getUsername());
    }
  }

  public static void testDelete() {
    ReportList reportList = new ReportList();
    User user = new User(UUID.randomUUID(), "david", "villa", "david@barca.com", "analyst");
    Report report = new Report(UUID.randomUUID(), user, "Daily Report", "Auto-generated Daily Summary");
    UUID reportId = report.getReportid();
    reportList.add(report);
    boolean deleted = reportList.delete(reportId);
    System.out.println("Report Delete: " + deleted);
    for (Report r : reportList.getAll()) {
      System.out.println("Report ID: " + r.getReportid());
      System.out.println("Name: " + r.getRpname());
    }
  }
}