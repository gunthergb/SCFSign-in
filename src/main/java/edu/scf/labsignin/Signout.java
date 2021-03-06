package edu.scf.labsignin;

import edu.scf.labsignin.util.CurrentTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Natox on 3/25/2017.
 */
public class Signout extends JDialog {

    private final long duration;

    Signout(long duration){
        this.duration = duration;
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.add(allContent());
        this.setModal(true);
        this.pack();
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);

    }

    private Container allContent(){
        Box d = Box.createVerticalBox();
        d.add(content());
        return d;
    }

    private Container content() {
        Box b = Box.createHorizontalBox();
        b.setBorder(new EmptyBorder(30, 30, 30, 30));
        b.add(this.checkbox());
        b.add(Box.createHorizontalStrut(20));


        String format = new SimpleDateFormat("m:s ").format(new Date(duration));

        b.add(this.messagePrompt(LoginFrame.firstName() + ' ' + LoginFrame.lastName() , format, CurrentTime.systemtime()));//, //totaltime));
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        return b;
    }

    private Component checkbox() {



        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////Timer///////////////////////////////////////////////////////////////////////////////////////////////
        {
            final Timer timer = new Timer((int) TimeUnit.SECONDS.toMillis(6), new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    dispose();
                    setVisible(false);
                    LoginFrame.clearField();
                    Timer t = (Timer) e.getSource();
                    t.stop();
                }
            });

            timer.start();
            //setVisible(true);
            System.out.println("Auto-Close Dialog");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////



        JLabel l = new JLabel(new ImageIcon(ClassLoader.getSystemResource("checkboxanim.gif")));
        return l;
    }

    private static String html(String content) {
        return "<HTML>" + content + "</HTML>";
    }

    private static String font(int size, String color, String content) {
        return "<font size=\"" + size + "\",color=\"" + color + "\">" + content + "</font>";
    }

    private static String rgb(Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }

    private static String row1(int size, Color c, String name) {
        return "Good Bye, " + font(size, rgb(c), name);
    }

    private static String row2(int size, Color c, String time) {
        return "Total time: " + font(size, rgb(c), time);
    }

    private static String row3(int size, Color c, String currentime) {
        return "Current time: " + font(size, rgb(c), currentime);
    }

    private Component messagePrompt(String name, String time, String currenttime){//, long totaltime) {
        byte size = 12;
        Color outer_color = Color.BLUE;
        Color keyvar_color = Color.RED;
        JLabel mP = new JLabel(html(font(size, rgb(outer_color), row1(size, keyvar_color, name) + "<br>" + row2(12, keyvar_color, time)) + "<br><hr noshade>" + font(4, rgb(outer_color), row3(4, keyvar_color, currenttime))));
        mP.setBackground(Color.WHITE);
        mP.setOpaque(true);
        mP.setMaximumSize(new Dimension(32767, 32767));
        return mP;
    }

    public static void main(String[] args) {
        new Signout(TimeUnit.MINUTES.toMillis(1) + TimeUnit.SECONDS.toMillis(30));
    }

}
