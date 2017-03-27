package edu.scf.labsignin;

import com.firebase.client.Firebase;

public final class FBTest {

    private static final String FIREBASE_URL = "https://lab-sign-in-database.firebaseio.com";

    public static Firebase root() {
        Firebase fb = new Firebase(FIREBASE_URL);
        Database.auth(fb);
        return fb;
    }

    private FBTest(){}


}
