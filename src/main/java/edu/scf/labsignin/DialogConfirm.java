package edu.scf.labsignin;

import edu.scf.labsignin.util.CurrentTime;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * Created by Natox on 3/18/2017.
 */
public class DialogConfirm extends JDialog implements ActionListener {

    DialogConfirm(){
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
        d.add(button());
        return d;
    }

    private Container content() {
        Box b = Box.createHorizontalBox();
        b.setBorder(new EmptyBorder(30, 30, 30, 30));
        b.add(this.checkbox());
        b.add(Box.createHorizontalStrut(20));
        b.add(this.messagePrompt(LoginFrame.firstName() + ' ' + LoginFrame.lastName() , CurrentTime.systemtime()));
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        return b;
    }

    private Component checkbox() {
        JLabel l = new JLabel(new ImageIcon(ClassLoader.getSystemResource("checkboxanim.gif")));
        return l;
    }

    private Component button(){

        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////Timer///////////////////////////////////////////////////////////////////////////////////////////////
        {
            final Timer timer = new Timer((int)TimeUnit.SECONDS.toMillis(10), e -> {
                dispose();
                setVisible(false);
                LoginFrame.clearField();
                Timer t = (Timer) e.getSource();
                t.stop();
            });

            timer.start();
            //setVisible(true);
            System.out.println("Auto-Close Dialog");
        }
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////

        JButton button = new JButton("CLOSE");
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
        return "Ok, " + font(size, rgb(c), name);
    }

    private static String row2() {
        return "You are signed in!";
    }

    private static String row3(int size, Color c, String time) {
        return "Current time: " + font(size, rgb(c), time);
    }

    private Component messagePrompt(String name, String time) {
        byte size = 12;
        Color outer_color = Color.BLUE;
        Color keyvar_color = Color.RED;
        JLabel mP = new JLabel(html(font(size, rgb(outer_color), row1(size, keyvar_color, name) + "<br>" + row2() + "<br><hr noshade>" + font(4, rgb(outer_color), row3(4, keyvar_color, time)))));
        mP.setBackground(Color.WHITE);
        mP.setOpaque(true);
        mP.setMaximumSize(new Dimension(32767, 32767));
        return mP;
    }

    public void actionPerformed(ActionEvent e) {
        dispose();
        LoginFrame.clearField();

    }

    public static void main(String[] args) {
        new DialogConfirm();
    }
}
