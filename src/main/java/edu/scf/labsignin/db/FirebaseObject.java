package edu.scf.labsignin.db;


import com.firebase.client.Firebase;

/**
 * A commons 'flag' to mark classes that handle working
 * with firebase under a known 'schema' or structure.
 * Ex: A "users" handle, knows that all its children may
 * be user-type objects, and know how to read/write user objects.
 */
public abstract class FirebaseObject {

    protected final Firebase ref;
    protected FirebaseObject(Firebase ref) {
        this.ref = ref;
    }

    public final Firebase getRef() {
        return ref;
    }

    @Override
    public String toString() {
        return ref.getPath().toString();
    }
}