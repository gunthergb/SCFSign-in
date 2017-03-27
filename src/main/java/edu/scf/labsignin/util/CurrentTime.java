package edu.scf.labsignin.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Gunther on 3/24/17.
 */
public class CurrentTime {

    public static String systemtime(){
        SimpleDateFormat sdf = new SimpleDateFormat("EE hh:mm a");
        String s = sdf.format(new Date());
        return s;

    }

}
