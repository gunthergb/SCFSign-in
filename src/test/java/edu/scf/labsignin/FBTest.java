package edu.scf.labsignin;

import com.firebase.client.Firebase;
import edu.scf.labsignin.db.DB;

public final class FBTest {

    private static final String FIREBASE_URL = "https://lab-sign-in-database.firebaseio.com";

    public static Firebase root() {
        Firebase fb = new Firebase(FIREBASE_URL);
        DB.auth(fb);
        return fb;
    }

    private FBTest(){}


}
