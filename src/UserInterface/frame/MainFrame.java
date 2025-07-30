package UserInterface.frame;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame(final Dimension dimension, final JPanel mainPanel) {
        super("Sudoku");
        this.setPreferredSize(dimension);
        this.setSize(dimension);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
