package edu.scf.labsignin.db;

import com.firebase.client.Firebase;
import edu.scf.labsignin.db.util.AsyncFirebaseReader;
import edu.scf.labsignin.db.util.AsyncFirebaseWriter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
        Date initial = val.getLoginTime();
        logHistory(val,time);
        AsyncFirebaseWriter.setValue(fb,null);
        return time.getTime() - initial.getTime();
    }

    private void logHistory(Student s, Date end) {
        HistoryEntry he = new HistoryEntry(s,end);
        String key = (s.getFirstName() + " " + s.getLastName()).toLowerCase();
        Firebase history = getRef().getParent().child("history");
        {//push to 'all'
            Firebase history_all = history.child("all");
            Firebase entry = history_all.push();
            AsyncFirebaseWriter.setValue(entry,he);
        }
        {//push to 'student'
            Firebase history_student = history.child("student").child(key);
            Firebase entry = history_student.push();
            AsyncFirebaseWriter.setValue(entry,he);
        }
        {//push to 'day'
            Date d = s.getLoginTime();
            DateFormat formater =  new SimpleDateFormat("yyyy/MMM/dd/hh");
            String path = formater.format(d);
            Firebase day = history.child("day").child(path);
            AsyncFirebaseWriter.setValue(day,he);
        }
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
