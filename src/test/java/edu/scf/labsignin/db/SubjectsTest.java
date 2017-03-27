package edu.scf.labsignin.db;

import com.firebase.client.Firebase;
import edu.scf.labsignin.FBTest;

import java.util.Arrays;

//Tests the subjects branch
public class SubjectsTest {

    public static void main(String[] args) throws InterruptedException {
        Firebase ROOT = FBTest.root();

        Firebase subjects = ROOT.child("subjects");
        Subjects handler = new Subjects(subjects);

        /*Subject[] ok = new Subject[] {
                new Subject("Subject A", "www.google.com/subA.png"),
                new Subject("Subject B", "www.google.com/subB.png"),
                new Subject("Subject C", "www.google.com/subC.png"),
                new Subject("Subject D", "www.google.com/subD.png"),
        };

        System.out.println(handler.setSubjects(ok));*/

     //   System.out.println(Arrays.toString(handler.getSubjects()));

        System.out.println("Getting...");
        handler.getSubjectsLater(s -> {
            System.out.println(Arrays.toString(s));
        });
        System.out.println("Waiting...");
        Thread.sleep(2000);

    }
}
