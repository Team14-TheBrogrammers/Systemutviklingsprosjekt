package no.brogrammers.systemutviklingsprosjekt.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Knut on 27.04.2016.
 */
public class SplashScreenForm extends JFrame {
    private JPanel mainPanel;
    private JLabel imageLabel;

    public SplashScreenForm() {
        setTitle("");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setContentPane(mainPanel);
        Image pic = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/loadingscreen_background.png"));
        imageLabel.setIcon(new ImageIcon(pic));
        setSize(732, 344);
        setUndecorated(true);
        setAlwaysOnTop(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
