package edu.scf.labsignin;

import javax.swing.*;
import java.awt.*;

/**
 * In this frame
 * it will show a greet message once clicked sign-in from the (login frame)(it should gather the person's entered name)
 * Have another message where it asks for the subject
 * Add a combobox with the subjects in it
 * Finally have a button that displays "OK"
 *
 */
public class SubjectSelector extends JFrame {

    SubjectSelector(){
        setUndecorated(true);
        setContentPane(content());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private Container content(){
        Box b = Box.createVerticalBox();
        b.add(topPanel());
        b.add(midPanel());
        b.add(botPanel());

        return b;
    }

    private JPanel topPanel(){
        JPanel tp = new JPanel();
        JLabel greet = new JLabel("Hi " );

        tp.add(greet);

        return tp;
    }

    private Container midPanel(){
        Box p = Box.createVerticalBox();
        JLabel message = new JLabel("Please, select the reason you are here: " );
        JComboBox subjects = new JComboBox();

        p.add(message);
        p.add(Box.createVerticalStrut(5));
        p.add(subjects);

        return p;

    }

    private JPanel botPanel(){
        JPanel bp = new JPanel();
        JButton b = new JButton("OK");
        bp.add(b);

        return bp;

    }

    public static void main(String[] args) {
        new SubjectSelector();
    }



}
