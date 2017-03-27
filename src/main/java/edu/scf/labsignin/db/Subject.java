package edu.scf.labsignin.db;

import com.firebase.client.Firebase;

/**
 * Represents a Subject in the database.
 *
 * SCHEMA:
 *     - name (String): the name of the subject.
 */
public final class Subject extends FirebaseObject {

    Subject(Firebase ref) {
        super(ref);
    }


}
