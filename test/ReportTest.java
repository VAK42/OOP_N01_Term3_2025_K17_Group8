import java.util.UUID;

public class ReportTest {

    public static void test() {
        System.out.println("=== TestReport ===");

        UUID reportid = UUID.randomUUID();

        UserEntity user = new UserEntity(UUID.randomUUID(), "reportUser", "pass123", "reportuser@example.com", "admin");

        ReportEntity report = new ReportEntity(reportid, user, "Báo cáo tháng 5", "Báo cáo chi tiết về doanh thu tháng 5");

        System.out.println("Report ID: " + report.getReportid());
        System.out.println("User username: " + report.getUser().getUsername());
        System.out.println("Report name: " + report.getRpname());
        System.out.println("Report info: " + report.getRpinfo());

        report.setRpname("Báo cáo tháng 6");
        report.setRpinfo("Báo cáo chi tiết về doanh thu tháng 6");

        System.out.println("Updated Report name: " + report.getRpname());
        System.out.println("Updated Report info: " + report.getRpinfo());

        System.out.println("=== End TestReport ===");
    }
}
