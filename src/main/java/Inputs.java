import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by Gunther on 3/22/17.
 */
public class Inputs {




    public static Object getSubjects() throws InterruptedException {
        Firebase fb = new Firebase("https://lab-sign-in-database.firebaseio.com/");
        auth(fb);
        Firebase user_dir = fb.child("Subjects");

        AtomicReference<Object> result = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);
        user_dir.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result.set(dataSnapshot.getValue());
                latch.countDown();
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {
                latch.countDown();
            }
        });
        latch.await();
        return result.get();
    }


    public static void main(String[] args) throws InterruptedException {

        System.out.println(getSubjects());




     /*   Firebase fb = new Firebase("https://lab-sign-in-database.firebaseio.com/");
        auth(fb);
        //fb.child("Users").child("Name/test").setValue("Feelsgoodman");



        String fname = "jamie";
        String last = "nub";

        Firebase user_dir = fb.child("Users").child(fname + "_" + last);
        User a = new User(fname,last);
        user_dir.setValue(a);

        *//*Firebase UID = fb.push(); //fb.push creates a new (unique) child to 'fb'

        UID.setValue("feelsgoodman");*//*
        Thread.sleep(10000000000l);*/

    }


    public static class User{


        static String fname;
        static String lname;
        public User(){


        }
        User(String fname, String lname){
            this.fname = fname;
            this.lname = lname;
        }
        public static String getFname() {
            return fname;
        }

        public static String getLname() {
            return lname;
        }

    }

    private static void auth(Firebase fb) {
        String token = Token.genToken();
        System.out.println("Token:" + token);
        fb.authWithCustomToken(token,null);
    }

}
