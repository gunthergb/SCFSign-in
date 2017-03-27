package edu.scf.labsignin.db;

import com.firebase.client.Firebase;
import edu.scf.labsignin.Token;

/**
 *
 */
public final class DB {

    private static final String FIREBASE_URL = "https://lab-sign-in-database.firebaseio.com";
    private static DB instance;

    private final Students STUDENTS;//Handles the strudents root in database.
    private final History HISTORY;
    private final Subjects SUBJECTS; //lists subjects

    private DB() {
        System.out.println("Loading DB...");
        Firebase ROOT = new Firebase(FIREBASE_URL);
        auth(ROOT);
        STUDENTS = new Students(ROOT.child("users"));
        HISTORY = new History(ROOT.child("history"));
        SUBJECTS = new Subjects(ROOT.child("subjects"));
        System.out.println("DB Loaded!");
    }

    public static void auth(Firebase mRef) {
        String token = Token.genToken();
        mRef.authWithCustomToken(token,null);//TODO verify auth was successful
    }

    //Force the DB to load now
    public static void init() {
        getInstance();
    }

    public static DB getInstance() {
        if(instance == null) {
            instance = new DB();
        }
        return instance;
    }

    public Subjects subjects() {
        return SUBJECTS;
    }
    public Students students() {
        return STUDENTS;
    }
}
