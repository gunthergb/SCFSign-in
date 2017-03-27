package edu.scf.labsignin.db;

/**
 * Represents a Subject in the database.
 *
 * SCHEMA:
 *     - name (String): the name of the subject.
 */
public final class Subject extends FirebaseObject implements SerializeContract {

    private String name;

    public Subject() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
