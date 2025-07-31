import UserInterface.frame.MainFrame;
import UserInterface.panel.MainPanel;

import javax.swing.*;
import java.awt.*;

public class UImain {
    public static void main(String[] args) {

        Dimension dimension = new Dimension(600,600);
        JPanel mainPanel = new MainPanel(dimension);
        JFrame mainFrame = new MainFrame(dimension, mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }
}
