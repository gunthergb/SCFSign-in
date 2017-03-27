package edu.scf.labsignin.db.util;

import com.firebase.client.Firebase;
import edu.scf.labsignin.Database;
import edu.scf.labsignin.db.Subject;

/**
 * Firebase Utility methods.
 */
public final class FirebaseUtil {

    private FirebaseUtil(){}//prevent construction

    //Debug
    public static void main(String[] args) throws InterruptedException {

        Firebase ROOT = new Firebase("https://lab-sign-in-database.firebaseio.com");
        Database.auth(ROOT);

        Firebase fb = ROOT.child("testing2");

        Subject s = new Subject();
        s.setName("lel");

        AsyncFirebaseWriter.setValueWithError(s,fb);//Write the value...
        Subject val = AsyncFirebaseReader.getValueWithError(fb,Subject.class);//Read the value...

        System.out.println(val);

        System.exit(1);

    }

    public static class User {
        private String first;

        public User() {}

        public User(String name) {
            first = name;
        }

        public String getFirst() {
            return first;
        }

        public void setFirst(String first) {
            this.first = first;
        }

        @Override
        public String toString() {
            return "User(name=" + first + ")";
        }
    }



    /**
     * Waits for data to finish uploading to firebase.
     * This should be called before the application exits.
     * Currently this just sleeps for 1000 ms.
     */
    public static void waitFB() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }


}
