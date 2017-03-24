import Util.ButtonUtil;
import org.jdesktop.swingx.prompt.PromptSupport;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Util.HTMLUtil.*;

/**
 * Created by Gunther on 3/23/17.
 */
public class ForgotSignIn extends JDialog implements ActionListener{


    ForgotSignIn(){
        this.setUndecorated(true);
        this.setAlwaysOnTop(true);
        this.add(allcontent());
        this.pack();
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);

    }

    private Component allcontent(){
        Box b = Box.createVerticalBox();
        b.setBorder(new EmptyBorder(10,10,10,10));
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        b.add(content());
        b.add(Box.createVerticalStrut(10));
        b.add(bot());
        b.add(Box.createVerticalStrut(10));
        b.add(buttons());
        return b;
    }

    private Component content(){
        Box b = Box.createHorizontalBox();
        b.setBackground(Color.WHITE);
        b.setOpaque(true);
        b.add(warningbox());
        b.add(Box.createHorizontalStrut(20));
        b.add(top());

        return b;
    }

    private Component top(){
        int Size = 14;
        int SizeSmall = 6;
        Color outer_color = Color.RED;
        Color inner_color = Color.BLUE;
        JLabel l = new JLabel();
        l.setText(html(font(Size, rgb(outer_color), "<b>" + "You forgot to Sign-in!" + "<br><hr noshade>" +
                       font(SizeSmall,rgb(inner_color), "It's ok, just tell us how long you've" + "<br>" + "been here."))));

        return l;
    }

    private Component warningbox(){
        JLabel l = new JLabel(new ImageIcon(ClassLoader.getSystemResource("warning.png")));
        return l;
    }

    private Component bot(){
        Box p = Box.createHorizontalBox();
        JTextField hour = new JTextField();
        PromptSupport.setPrompt("hour", hour);
        hour.setFont(hour.getFont().deriveFont(Font.BOLD, 24f));
        hour.setMargin(new Insets(5,5,5,5));
        JTextField min = new JTextField();
        PromptSupport.setPrompt("min", min);
        min.setMargin(new Insets(5,5,5,5));
        min.setFont(min.getFont().deriveFont(Font.BOLD, 24f));
        p.add(hour);
        p.add(Box.createHorizontalStrut(10));
        p.add(min);
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        p.setMaximumSize(new Dimension(300,40));

        return p;
    }

    private Component buttons(){
        Box b = Box.createVerticalBox();
        JButton _continue = ButtonUtil.flatButton(html(font(6, rgb(Color.BLACK), "<b>" +"Continue")), Color.GREEN.darker());
        _continue.setIcon(new ImageIcon(ClassLoader.getSystemResource("next.png")));
        _continue.setHorizontalTextPosition(SwingConstants.LEFT);
        JButton again = ButtonUtil.flatButton(html(font(6, rgb(Color.BLACK), "<b>" + "Try Again")), new Color(0xFDEA38));
        again.setIcon(new ImageIcon(ClassLoader.getSystemResource("refresh.png")));
        again.setHorizontalTextPosition(SwingConstants.LEFT);

        b.add(_continue);
        _continue.setAlignmentX(Component.CENTER_ALIGNMENT);
        _continue.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        b.add(Box.createVerticalStrut(5));
        again.setAlignmentX(Component.CENTER_ALIGNMENT);
        again.setMaximumSize(new Dimension(Short.MAX_VALUE, Short.MAX_VALUE));
        b.add(again);

        return b;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public static void main(String[] args) {
        new ForgotSignIn();
    }
}
