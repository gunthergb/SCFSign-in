package edu.scf.labsignin.db;

import com.firebase.client.Firebase;

public final class History {

    private final Firebase root;//The relative root we work with.
    History(Firebase root) {//package-private
        this.root = root;
    }

}
