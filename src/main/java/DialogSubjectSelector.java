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
        return "Hi, " + font(size, rgb(c), name);
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
        JComboBox subjects = new JComboBox(classSubjects);
        subjects.setSelectedIndex(0);
        setFlatBox(subjects);
        //subjects.setBorder(new EmptyBorder(0,0,0,0));
        p.setBackground(Color.WHITE);
        p.setOpaque(true);
        //p.add(Box.createVerticalStrut(5));
        p.add(subjects);

        return p;

    }

    private static void setFlatBox(JComboBox box){
        UIDefaults d = new UIDefaults();
//     putClientProperty("Nimbus.Overrides", d);
//     putClientProperty("Nimbus.Overrides.InheritDefaults", false);
//     JComponent c = (JComponent) getEditor().getEditorComponent();
//     c.putClientProperty("Nimbus.Overrides", d);
//     c.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
//     c.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        Painter<JComponent> emptyPainter = new Painter<JComponent>() {
            public void paint(Graphics2D g, JComponent c, int w, int h) {
        /* Empty painter */
            }
        };
        d.put("TextField.borderPainter", emptyPainter);
        d.put("TextField[Enabled].borderPainter", emptyPainter);
        d.put("TextField[Focused].borderPainter", emptyPainter);
        d.put("ComboBox:\"ComboBox.textField\"[Enabled].backgroundPainter", emptyPainter);
        d.put("ComboBox:\"ComboBox.textField\"[Selected].backgroundPainter", emptyPainter);
        d.put("ComboBox[Editable+Focused].backgroundPainter", emptyPainter);
        box.putClientProperty("Nimbus.Overrides", d);
        JComponent c = (JComponent) box.getEditor().getEditorComponent();
        c.putClientProperty("Nimbus.Overrides", d);
        c.setBorder(BorderFactory.createLineBorder(Color.BLACK));

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
        dispose();
        new DialogConfirm();
    }

    public static void main(String[] args) {
        new DialogSubjectSelector();
    }

    /*class EditorCombo extends JComboBox<String> {
        public EditorCombo() {
            super();
            setEditable(true);
            for (int i = 0; i < 10; i++) {
                addItem("Sample" + i);
            }
        }
        @Override public void updateUI() {
            //super.updateUI();
            setUI(new javax.swing.plaf.synth.SynthComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    JButton button = new JButton() {
                        @Override
                        public int getWidth() {
                            return 0;
                        }
                    };
                    button.setBorder(BorderFactory.createEmptyBorder());
                    button.setVisible(false);
                    return button;
                }
                @Override
                public void configureArrowButton() {
                }
            });
            UIDefaults d = new UIDefaults();
//     putClientProperty("Nimbus.Overrides", d);
//     putClientProperty("Nimbus.Overrides.InheritDefaults", false);
//     JComponent c = (JComponent) getEditor().getEditorComponent();
//     c.putClientProperty("Nimbus.Overrides", d);
//     c.putClientProperty("Nimbus.Overrides.InheritDefaults", false);
//     c.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            Painter<JComponent> emptyPainter = new Painter<JComponent>() {
                public void paint(Graphics2D g, JComponent c, int w, int h) {
        *//* Empty painter *//*
                }
            };
            d.put("TextField.borderPainter", emptyPainter);
            d.put("TextField[Enabled].borderPainter", emptyPainter);
            d.put("TextField[Focused].borderPainter", emptyPainter);
            d.put("ComboBox:\"ComboBox.textField\"[Enabled].backgroundPainter", emptyPainter);
            d.put("ComboBox:\"ComboBox.textField\"[Selected].backgroundPainter", emptyPainter);
            d.put("ComboBox[Editable+Focused].backgroundPainter", emptyPainter);
            putClientProperty("Nimbus.Overrides", d);
            JComponent c = (JComponent) getEditor().getEditorComponent();
            c.putClientProperty("Nimbus.Overrides", d);
            c.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        }
    }*/
}
