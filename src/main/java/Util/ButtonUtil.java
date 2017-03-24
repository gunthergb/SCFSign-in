package Util;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Gunther on 3/23/17.
 */
public class ButtonUtil {

    public static JButton flatButton(String text, Color rgb){
        JButton flatButton = new JButton(text);
        flatButton.setBackground(rgb);
        flatButton.setFocusPainted(false);
        flatButton.setBorderPainted(false);
        flatButton.setContentAreaFilled(false);
        flatButton.setOpaque(true);

        return flatButton;
    }


}
