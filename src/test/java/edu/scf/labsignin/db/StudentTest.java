package edu.scf.labsignin.db;

import com.firebase.client.Firebase;
import edu.scf.labsignin.FBTest;

import java.util.Date;

//Tests the students branch
public class StudentTest {

    public static void main(String[] args) {
        Firebase root = FBTest.root();
        Firebase studentsfb = root.child("loggedin");
        test(new Students(studentsfb));
    }

    private static void test(Students students) {

        if(students.isLoggedIn("jamie","nub2")) {
            System.out.println("Already logged in!");
        } else {

            try {
                if (students.login("jamie", "nub2", "eating pizza with amy", new Date())) {
                    System.out.println("Logged In!");
                } else {
                    System.out.println("EEK");
                }
            } catch (DBException dbe) { //Should not happen if we were not logged in..
                System.out.println("User already logged in, why did we call this???");
            }

        }


    }
}
