package edu.scf.labsignin.db;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class HistoryEntry extends FirebaseObject implements SerializeContract {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("hh:mm:ss");

    private String firstName;
    private String lastName;
    private String subjectName;
    private Date loginTime;
    private Date logoutTime;
    private String durationStr;

    public HistoryEntry() {}

    HistoryEntry(Student s, Date end) {
        this(s.getFirstName(),s.getLastName(),
                s.getSubjectName(),s.getLoginTime(),
                end);
    }
    HistoryEntry(String firstName,//Package-private
            String lastName,
            String subjectName,
            Date loginTime,
            Date logoutTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectName = subjectName;
        this.loginTime = loginTime;
        this.logoutTime = logoutTime;
        this.durationStr = duration(loginTime,logoutTime);
    }

    private static String duration(Date in, Date out) {
        Date dur = new Date(out.getTime()-in.getTime());
        return DATE_FORMAT.format(dur);
    }

    //////////////////////////////////////////////////////////////////////

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
    public Date getLoginTime() {
        return loginTime;
    }
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }
    public Date getLogoutTime() {
        return logoutTime;
    }
    public void setLogoutTime(Date logoutTime) {
        this.logoutTime = logoutTime;
    }
    public String getDurationStr() {
        return durationStr;
    }
    public void setDurationStr(String durationStr) {
        this.durationStr = durationStr;
    }

    //////////////////////////////////////////////////////////////////////

}
