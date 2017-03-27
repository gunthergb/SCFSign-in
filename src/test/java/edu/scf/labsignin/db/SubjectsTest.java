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


       String[] classSubjects = {"CGS 1000: Computer Info Systems", "CGS 1570: Integrated Business Apps",
                "CGS 2820C: Web Page Development", "COP 2510: Programming Concepts", "COP 2170: Visual Basic Programming",
                "COP 2250: Java Programming I and II", "Other"};

       Subject[] sub = new Subject[classSubjects.length];
        for (int i = 0; i < sub.length; i++) {
            sub[i] = new Subject(classSubjects[i]);
        }

        handler.setSubjects(null);
        handler.setSubjects(sub);
        /*Subject[] ok = new Subject[] {
                new Subject("Subject A", "www.google.com/subA.png"),
                new Subject("Subject B", "www.google.com/subB.png"),
                new Subject("Subject C", "www.google.com/subC.png"),
                new Subject("Subject D", "www.google.com/subD.png"),
        };

        System.out.println(handler.setSubjects(ok));*/

     //   System.out.println(Arrays.toString(handler.getSubjects()));

        System.out.println("Getting...");
        handler.getSubjectsAndListen(s -> {
            System.out.println(Arrays.toString(s));
        });
        System.out.println("Waiting...");
        Thread.sleep(2000);

    }
}
