package edu.scf.labsignin;

import edu.scf.labsignin.db.DB;

/**
 * Created by Gunther on 3/15/17.
 */


//noob
public class Main {


    public static void main(String[] args) {
        DB.init();
        new LoginFrame();
    }

}
