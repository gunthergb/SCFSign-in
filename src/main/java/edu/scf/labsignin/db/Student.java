package edu.scf.labsignin.db;

import java.util.Date;

public final class Student extends FirebaseObject implements SerializeContract {

    private String firstName;
    private String lastName;
    private String subjectName;
    private Date loginTime;

    public Student() {}

    Student(String firstName,//Package-private
            String lastName,
            String subjectName,
            Date loginTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.subjectName = subjectName;
        this.loginTime = loginTime;
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

    //////////////////////////////////////////////////////////////////////

}
