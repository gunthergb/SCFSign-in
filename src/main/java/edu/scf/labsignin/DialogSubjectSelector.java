package edu.scf.labsignin;

import edu.scf.labsignin.util.CurrentTime;
import com.firebase.client.Firebase;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Gunther on 3/21/17.
 */
public class DialogSubjectSelector extends JDialog implements ActionListener {

    DialogSubjectSelector(){
        this.setLayout(new BorderLayout());
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.add(allcontent());
        this.setModal(true);
        this.pack();
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
    }

    public static JComboBox subjects;

    public static String time;

    public static String[] classSubjects = {"CGS 1000: Computer Info Systems", "CGS 1570: Integrated Business Apps",
            "CGS 2820C: Web Page Development", "COP 2510: Programming Concepts", "COP 2170: Visual Basic Programming",
            "COP 2250: Java Programming I and II", "Other"};

    private Container allcontent(){
        Box b = Box.createVerticalBox();
        b.add(content());
        b.add(midPanel());
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        b.add(Box.createVerticalStrut(10));
        b.add(button());
        return b;
    }

    private Container content() {
        Box b = Box.createHorizontalBox();
        b.setBorder(new EmptyBorder(30, 30, 30, 30));
        b.add(this.messagePrompt(LoginFrame.firstName() + ' ' + LoginFrame.lastName()));
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        return b;
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
        return "Hi, " + font(size, rgb(c), name) + " !";
    }


    private static String row3() {
        return "Please select the reason you are here: ";
    }

    private Component messagePrompt(String name) {
        byte size = 16;
        Color outer_color = Color.BLUE;
        Color keyvar_color = Color.RED;
        JLabel mP = new JLabel(html(font(size, rgb(outer_color), row1(size, keyvar_color, name) + "<br><hr noshade>" + font(4, rgb(outer_color), row3()))));
        mP.setBackground(Color.WHITE);
        mP.setOpaque(true);
        mP.setMaximumSize(new Dimension(32767, 32767));
        return mP;
    }

    private Container midPanel(){
        Box p = Box.createVerticalBox();
        subjects = new JComboBox(classSubjects);
        //JComboBox subjects = new JComboBox(classSubjects);
        subjects.setSelectedIndex(0);
        p.setBackground(Color.WHITE);
        p.setOpaque(true);
        p.add(subjects);

        return p;

    }

    private Component button(){
        JButton button = new JButton("OK");
        button.setFont(button.getFont().deriveFont(Font.BOLD,18f));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBackground(Color.RED);
        button.setOpaque(true);

        button.addActionListener(this);

        return button;

    }


    public void actionPerformed(ActionEvent e) {

        Firebase mref = new Firebase("https://lab-sign-in-database.firebaseio.com/");
        auth(mref);

        String firstname = LoginFrame.firstname.getText().trim();
        String lastname = LoginFrame.lastname.getText().trim();
        Object classSubjects = subjects.getSelectedItem();
        time = CurrentTime.systemtime();
        //String time = CurrentTime.systemtime();


        //Name name = new Name();

        Firebase user_dir = mref.child("Users").child(firstname + "_" + lastname);
        Firebase user_his = mref.child("Users History").child(firstname + "_" + lastname);
        Name a = new Name(firstname, lastname, classSubjects, time);

        a.setLname(lastname);
        a.setFname(firstname);
        a.setClassSubjects(classSubjects);
        a.setTime(time);

        user_dir.setValue(a);
        user_his.setValue(a);

        //mref.child("Users").setValue(name);

        //Database.saveData();
        dispose();
        new DialogConfirm();
    }

    public class Name {
        private String firstname;
        private String lastname;
        private Object classSubjects;
        private String time;

        public Name() {}

        Name(String fname, String lname, Object classSubjects, String time){
            this.firstname = fname;
            this.lastname = lname;
            this.classSubjects = (String) classSubjects;
            this.time = time;
        }

        public String getName() {
            return firstname;
        }

        public String getLname(){
            return lastname;
        }

        public String getClassSubjects(){
            return (String) classSubjects;
        }

        public String getTime(){
            return time;
        }

        public void setLname(String lastname) {
            this.lastname = lastname;
        }

        public void setFname(String firstname){
            this.firstname = firstname;
        }

        public void setClassSubjects(Object classSubjects){
            this.classSubjects = classSubjects;
        }

        public void setTime(String time){
            this.time = time;
        }

    }

    public static void auth(Firebase mRef) {
        String token = Token.genToken();
        System.out.println("Token:" + token);
        mRef.authWithCustomToken(token,null);
    }

    public static void main(String[] args) {
        new DialogSubjectSelector();
    }
}
