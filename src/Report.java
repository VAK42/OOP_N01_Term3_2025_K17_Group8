import java.util.UUID;

public class Report {
  private UUID reportid;
  private User user;
  private String rpname;
  private String rpinfo;

  public Report(UUID reportid, User user, String rpname, String rpinfo) {
    this.reportid = reportid;
    this.user = user;
    this.rpname = rpname;
    this.rpinfo = rpinfo;
  }

  public UUID getReportid() {
    return reportid;
  }

  public void setReportid(UUID reportid) {
    this.reportid = reportid;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRpname() {
    return rpname;
  }

  public void setRpname(String rpname) {
    this.rpname = rpname;
  }

  public String getRpinfo() {
    return rpinfo;
  }

  public void setRpinfo(String rpinfo) {
    this.rpinfo = rpinfo;
  }
}