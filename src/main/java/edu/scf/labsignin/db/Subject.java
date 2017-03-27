package edu.scf.labsignin.db;

/**
 * Represents a Subject in the database.
 *
 * SCHEMA:
 *     - name (String): the name of the subject.
 */
public final class Subject extends FirebaseObject implements SerializeContract {

    private String name;
    private String icon;//The icon for this subject (optional)

    public Subject() {}

    public Subject(String name) {
        this.name = name;
    }
    public Subject(String name, String icon){
        this.name = name;
        this.icon = icon;
    }


    //////////////////////////////////////////////////////////////////////

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }
    public void setIcon(String icon) {
        this.icon = icon;
    }

    //////////////////////////////////////////////////////////////////////

    @Override
    public String toString() {
        return super.toString() + ":[name=" + name + ",icon=" + icon + "]";
    }
}
