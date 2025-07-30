package UserInterface.panel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class SudokuPanel extends JPanel {

    // cada setor do board que contém 9 espaços
    public SudokuPanel(){
        Dimension dimension = new Dimension(170,170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(Color.blue, 2, true));
        this.setVisible(true);
    }
}
