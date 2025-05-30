import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReportList {
  private final List<Report> reports = new ArrayList<>();

  public void add(Report report) {
    reports.add(report);
  }

  public boolean edit(UUID reportid, User newUser, String newName, String newInfo) {
    for (Report r : reports) {
      if (r.getReportid().equals(reportid)) {
        r.setUser(newUser);
        r.setRpname(newName);
        r.setRpinfo(newInfo);
        return true;
      }
    }
    return false;
  }

  public boolean delete(UUID reportid) {
    return reports.removeIf(r -> r.getReportid().equals(reportid));
  }

  public List<Report> getAll() {
    return new ArrayList<>(reports);
  }
}