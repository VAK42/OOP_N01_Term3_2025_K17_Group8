import java.util.UUID;

public class ReportEntity {

    private UUID reportid;
    private UserEntity user;
    private String rpname;
    private String rpinfo;

    public ReportEntity(UUID reportid, UserEntity user, String rpname, String rpinfo) {
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
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
