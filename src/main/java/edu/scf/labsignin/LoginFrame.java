package edu.scf.labsignin;

import edu.scf.labsignin.util.MyDocumentFilter;
import com.firebase.client.*;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * This is the main login form...
 * That will contain the SCF LOGO
 * A small message
 * First Name:
 * Last Name:
 * Sign in Button
 * Sign out Button
 */
public class LoginFrame extends JFrame {

    public static JTextField firstname;
    public static JTextField lastname;

    LoginFrame(){
        if(! setLAF()){
            System.out.println("Failed to set LAF");
            return;
        }
        this.setTitle("Lab Sign-in");
        this.setContentPane(wrappContent());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.pack();
        this.setVisible(true);
    }

    public static String firstName(){
        return firstname.getText();
    }

    public static String lastName(){
        return lastname.getText();
    }

    private static boolean setLAF(){
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("Button.focus", Color.BLUE);
                    //Combobox
                    UIManager.put("ComboBox.border", new LineBorder(Color.RED));

                    return true;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        return false;
    }

    private Container wrappContent(){
        JPanel p = new JPanel(new GridBagLayout());
        p.add(createContent());
        p.setBackground(new Color(255,144,0,200));

        return p;
    }

    private static JPanel createRounded(final Dimension cornerSize){
        JPanel p = new JPanel() {
            public Insets getInsets(){
                return new Insets(cornerSize.height, cornerSize.width, cornerSize.height, cornerSize.width);

            }
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Dimension arcs = cornerSize; //Border corners arcs {width,height}, change this to whatever you want
                int width = getWidth();
                int height = getHeight();
                Graphics2D graphics = (Graphics2D) g;
                graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


                //Draws the rounded panel with borders.
                graphics.setColor(getBackground());
                graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
                //graphics.setColor(getForeground());
                graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
            }
        };
        p.setOpaque(false);
        return p;
    }

    ///BYPASSING FILTERS

    public static void clearField(){
        firstname.setText("");
        lastname.setText("");
    }
    ////////////////////

    public Container createContent(){
        JPanel maincontent = createRounded(new Dimension(15,15));
        maincontent.setLayout(new BoxLayout(maincontent,BoxLayout.Y_AXIS));
        JPanel logo = new JPanel();
        JPanel message = new JPanel();
        JPanel name = new JPanel();

        {
            maincontent.setBackground(new Color(238,238,238));
            maincontent.add(logo);
            maincontent.add(Box.createVerticalStrut(30));
            maincontent.add(message);
            maincontent.add(Box.createVerticalStrut(30));
            maincontent.add(name);
            maincontent.add(Box.createVerticalStrut(30));
        }

        {
            Image i = null;
            try {
                 i = ImageIO.read(ClassLoader.getSystemResourceAsStream("scf-real.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            logo.add(new JLabel(new ImageIcon(i)));
            logo.setBackground(null);
            logo.setPreferredSize(new Dimension(300,100));
        }

        {
            message.setLayout(new BoxLayout(message, BoxLayout.Y_AXIS));

            {
                JLabel topL = new JLabel("Welcome to the Lab!");
                topL.setFont(topL.getFont().deriveFont(20f));
                topL.setAlignmentX(CENTER_ALIGNMENT);
                topL.setHorizontalAlignment(SwingConstants.CENTER);
                message.add(topL);
            }
            message.add(Box.createVerticalStrut(10));
            {
                JLabel botL = new JLabel("<HTML><font color=\"orange\", size=\"4\"><b>Please </font><U><font color = \"red\", size=\"5\">Sign-in</U><<b></font></HTML>");
                botL.setAlignmentX(CENTER_ALIGNMENT);
                botL.setHorizontalAlignment(SwingConstants.CENTER);
                message.add(botL);
            }
            message.setBackground(new Color(238,238,238));
        }

        {
            name.setBackground(null);
            name.setLayout(new BoxLayout(name, BoxLayout.Y_AXIS));
            {
                firstname = new JTextField();
                PromptSupport.setPrompt("First Name", firstname);
                firstname.setFont(firstname.getFont().deriveFont(24f));
                firstname.setMargin(new Insets(5,5,5,5));
                firstname.setAlignmentX(Component.CENTER_ALIGNMENT);
                firstname.setBackground(Color.WHITE);
                name.add(firstname);

                //Recognition
                ((AbstractDocument) firstname.getDocument()).setDocumentFilter(new MyDocumentFilter());
            }
            name.add(Box.createVerticalStrut(10));
            {
                lastname = new JTextField();
                PromptSupport.setPrompt("Last Name", lastname);
                lastname.setFont(lastname.getFont().deriveFont(24f));
                lastname.setBackground(Color.WHITE);
                lastname.setMargin(new Insets(5,5,5,5));
                lastname.setAlignmentX(Component.CENTER_ALIGNMENT);
                name.add(lastname);

                ((AbstractDocument) lastname.getDocument()).setDocumentFilter(new MyDocumentFilter());
            }
        }

        {
            JButton b1 = new JButton("Sign-in");
            b1.setFont(b1.getFont().deriveFont(Font.BOLD, 20f));
            b1.setAlignmentX(Component.CENTER_ALIGNMENT);
            b1.setMinimumSize(new Dimension(0, 70));
            b1.setPreferredSize(new Dimension(0,50));
            b1.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            b1.setBorderPainted(false);
            b1.setFocusPainted(false);
            b1.setContentAreaFilled(false);
            b1.setBackground(new Color(10,201,134));
            b1.setOpaque(true);


            //Action
            {
                b1.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (firstname.getText().equals("") || lastname.getText().equals(""))
                        {
                            JOptionPane optionPane = new JOptionPane("Please fill in the first name and last name", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Fill in textboxes");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                        }
                        else
                        {
                            //firstname.getText() = Inputs.User.getFname();
                            Inputs.User.getLname();
                            new DialogSubjectSelector();
                        }
                    }
                });
            }


            final JButton b2 = new JButton("Sign-out");
            b2.setBorderPainted(false);
            b2.setFont(b1.getFont().deriveFont(Font.BOLD, 20f));
            b2.setAlignmentX(Component.CENTER_ALIGNMENT);
            b2.setMinimumSize(new Dimension(0, 70));
            b2.setPreferredSize(new Dimension(0,50));
            b2.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
            b2.setFocusPainted(false);
            b2.setContentAreaFilled(false);
            b2.setBackground(Color.RED);
            b2.setOpaque(true);

            //Action
            {
                b2.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (firstname.getText().equals("") || lastname.getText().equals(""))
                        {
                            JOptionPane optionPane = new JOptionPane("Please fill in the first name and last name", JOptionPane.ERROR_MESSAGE);
                            JDialog dialog = optionPane.createDialog("Fill in textboxes");
                            dialog.setAlwaysOnTop(true);
                            dialog.setVisible(true);
                        }
                        else
                        {
                            //firstname.getText() = Inputs.User.getFname();
                            Inputs.User.getLname();

                            Firebase mref = new Firebase("https://lab-sign-in-database.firebaseio.com/");
                            DialogSubjectSelector.auth(mref);
                            String firstname = LoginFrame.firstname.getText().trim();
                            String lastname = LoginFrame.lastname.getText().trim();

                            Firebase listOfObjects = mref.orderByChild(firstname + "_" + lastname).getRef();

                            listOfObjects.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    dataSnapshot.getRef().removeValue();
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });

                            //Query removeQuery = mref.child("Users").child(firstname + "_" + lastname).equalTo(firstname + "_" + lastname);

                            /*removeQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                                        appleSnapshot.getRef().removeValue();
                                    }
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {
                                    System.out.println("Cancelled");
                                }
                            });*/




                            new Signout();
                        }
                    }
                });
            }


            maincontent.add(b1);
            maincontent.add(Box.createVerticalStrut(20));
            maincontent.add(b2);
        }

        return maincontent;
    }

}
