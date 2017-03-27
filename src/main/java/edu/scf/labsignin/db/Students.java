package edu.scf.labsignin.db;

import com.firebase.client.Firebase;

/**
 * Handles the "ROOT/Students" branch in FireBase.
 */
public final class Students {

    private final Firebase root; //The relative root that we operate upon.
    Students(Firebase root) { //package-private
        this.root = root;
    }
}
