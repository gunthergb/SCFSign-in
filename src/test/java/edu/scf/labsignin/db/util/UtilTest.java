package edu.scf.labsignin.db.util;

import com.firebase.client.Firebase;
import edu.scf.labsignin.Database;
import edu.scf.labsignin.db.Subject;

import edu.scf.labsignin.FBTest;

//Test database utility
public class UtilTest {

    //Debug
    public static void main(String[] args) throws InterruptedException {

        Firebase ROOT = FBTest.root();
        Database.auth(ROOT);

        Firebase fb = ROOT.child("testing2");

        Subject s = new Subject();
        s.setName("lel");

        AsyncFirebaseWriter.setValueWithError(fb,s);//Write the value...
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

}
