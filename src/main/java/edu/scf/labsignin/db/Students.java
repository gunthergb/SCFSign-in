package edu.scf.labsignin.db;

import com.firebase.client.Firebase;
import edu.scf.labsignin.db.util.AsyncFirebaseReader;
import edu.scf.labsignin.db.util.AsyncFirebaseWriter;

import java.util.Date;

/**
 * Handles the "ROOT/Students" branch in FireBase.
 */
public final class Students extends FirebaseObject {

    Students(Firebase ref) { //package-private
        super(ref);
    }


    public boolean login(String firstName,
                      String lastName,
                      String subject,
                      Date date) throws DBException {
        Student s = new Student(firstName,lastName,subject,date);
        Firebase fb = getRoot(firstName,lastName);
        ensureNotLoggedIn(fb);
        return AsyncFirebaseWriter.setValue(fb,s);
    }

    public long logout(String firstName,
                          String lastName,
                          Date time) throws DBException {
        Firebase fb = getRoot(firstName,lastName);
        Student val = AsyncFirebaseReader.getValue(fb,Student.class);
        if(val == null) throw new DBException("User '" + fb.getKey() + "' is not logged in!");
        AsyncFirebaseWriter.setValue(fb,null);
        //TODO move to history
        Date initial = val.getLoginTime();
        return time.getTime() - initial.getTime();
    }

    public boolean isLoggedIn(String first, String last) {
        Firebase fb = getRoot(first,last);
        return AsyncFirebaseReader.getValue(fb,Object.class) != null;
    }

    private void ensureNotLoggedIn(Firebase fb) {
        Object val = AsyncFirebaseReader.getValue(fb,Object.class);
        if(val != null) throw new DBException("User '" + fb.getKey() + "' already logged in");
    }
    private void ensureLoggedIn(Firebase fb) {
        Object val = AsyncFirebaseReader.getValue(fb,Object.class);
        if(val == null) throw new DBException("User '" + fb.getKey() + "' is not logged in");
    }


    private Firebase getRoot(String first, String last) {
        String key = (first + " " + last).toLowerCase();
        return getRef().child(key);
    }
}
