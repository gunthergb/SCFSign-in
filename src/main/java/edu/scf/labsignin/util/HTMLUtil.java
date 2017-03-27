package edu.scf.labsignin.util;

import java.awt.*;

/**
 * Created by Gunther on 3/23/17.
 */
public class HTMLUtil {

    public static String html(String content) {
        return "<HTML>" + content + "</HTML>";
    }
    public static String font(int size, String color, String content){
        return "<font size=\"" + size + "\"" + ",color=\"" + color + "\">" + content  + "</font>";
    }
    public static String rgb(Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }

    public static String row1(int size, Color c, String name) {
        return "Ok, " + font(size,rgb(c),name);
    }
    public static String row2() {
        return "You are signed in!";
    }
    public static String row3(int size, Color c, String time) {
        return "Current time: " + font(size,rgb(c),time);
    }
    
}
