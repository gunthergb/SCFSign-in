package edu.scf.labsignin.db;

import com.firebase.client.Firebase;

/**
 *
 */
public final class DB {

    private static final String FIREBASE_URL = "";
    private static DB instance;

    private final Students STUDENTS;//Handles the strudents root in database.
    private final History HISTORY;
    private final Subjects SUBJECTS; //lists subjects

    private DB() {
        Firebase ROOT = new Firebase(FIREBASE_URL);
        STUDENTS = new Students(ROOT.child("users"));
        HISTORY = new History(ROOT.child("history"));
        SUBJECTS = new Subjects(ROOT.child("subjects"));
    }

    public static DB getInstance() {
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }



}
