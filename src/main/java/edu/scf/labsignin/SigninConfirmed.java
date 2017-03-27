package edu.scf.labsignin;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

/**
 * In this frame, it will show a cheeky gif
 * Also have their name and the current time.
 */
public class SigninConfirmed extends JPanel {

    SigninConfirmed(){
        this.setLayout(new BorderLayout());
        this.add(allContent());
    }

    private Container allContent(){
        Box d = Box.createVerticalBox();
        d.add(content());
        d.add(button());
        return d;
    }
    private Container content(){
        Box b = Box.createHorizontalBox();
        b.setBorder(new EmptyBorder(30,30,30,30));
        b.add(checkbox());
        b.add(Box.createHorizontalStrut(20));
        b.add(messagePrompt("Bob", "7:00AM"));
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        return b;
    }

    private Component checkbox(){
        JLabel l = new JLabel(new ImageIcon(ClassLoader.getSystemResource("checkboxanim.gif")));
        return l;
    }

    private Component button(){
        JButton button = new JButton("CLOSE");
        button.setFont(button.getFont().deriveFont(Font.BOLD,18f));
        button.setAlignmentX(CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBackground(Color.RED);
        button.setOpaque(true);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //SigninConfirmed.this.show(SigninConfirmed)
                //SigninConfirmed.this.dispatchEvent(new WindowEvent(SigninConfirmed.this, WindowEvent.WINDOW_CLOSING));
            }
        });

        return button;
    }



    private static String html(String content) {
        return "<HTML>" + content + "</HTML>";
    }
    private static String font(int size, String color, String content){
        return "<font size=\"" + size + "\"" + ",color=\"" + color + "\">" + content  + "</font>";
    }
    private static String rgb(Color c) {
        return "rgb(" + c.getRed() + "," + c.getGreen() + "," + c.getBlue() + ")";
    }

    private static String row1(int size, Color c, String name) {
        return "Ok, " + font(size,rgb(c),name);
    }
    private static String row2() {
        return "You are signed in!";
    }
    private static String row3(int size, Color c, String time) {
        return "Current time: " + font(size,rgb(c),time);
    }

    private Component messagePrompt(String name, String time){
        int size = 12;
        Color outer_color = Color.BLUE;
        Color keyvar_color = Color.RED;
        JLabel mP = new JLabel(
                html( font(size,rgb(outer_color),
                        row1(size,keyvar_color,name) + "<br>" +
                        row2() + "<br><hr noshade>" +
                        font(4,rgb(outer_color),row3(4,keyvar_color,time)))//NOTE: <hr> tab breaks outside font tag...
                ));
        mP.setBackground(Color.WHITE);
        mP.setOpaque(true);
        mP.setMaximumSize(new Dimension(Short.MAX_VALUE,Short.MAX_VALUE));
        return mP;
    }

    public void show(Frame owner){
        JDialog d = new JDialog(owner, true);
        //d.setUndecorated(true);
        //d.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        d.setContentPane(this);
        d.pack();
        d.setLocationRelativeTo(owner);
        d.setVisible(true);
    }
    /*public static void main(String[] args) {
        new SigninConfirmed().show(new LoginFrame());
    }*/

}
