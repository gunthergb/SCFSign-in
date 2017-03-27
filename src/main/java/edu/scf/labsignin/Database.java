package edu.scf.labsignin;

import com.firebase.client.Firebase;

/**
 * Created by Natox on 3/25/2017.
 */
public class Database {

    private static Firebase mRef;

    public void connectData(){
        mRef = new Firebase("https://lab-sign-in-database.firebaseio.com/");
        auth(mRef);
    }

    public void saveData(){
        connectData();
        //Firebase user_dir = mRef.child("Users");

        String key = mRef.push().getKey();
        //String getfname = LoginFrame.firstname.getText();
        //String glname = LoginFrame.lastname.getText();

        //User user = new User(getF);
        //User.setName();

        //mRef.child("Users").setValue(user);

    }

    public static class User{


        private String fname;
        private String lname;
        public User(){}

        public User(String fname, String lname){
            this.fname = fname;
            this.lname = lname;
        }
        public String getFname() {
            return fname = LoginFrame.firstname.getText();
        }

        public String getLname() {
            return lname = LoginFrame.lastname.getText();
        }

        /*public static void setName(){
            getFname();
            getLname();
        }*/

    }

    public static void auth(Firebase mRef) {
        String token = Token.genToken();
        System.out.println("Token:" + token);
        mRef.authWithCustomToken(token,null);
    }
}
