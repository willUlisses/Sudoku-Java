package UserInterface.panel;

import UserInterface.input.NumberText;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class SudokuPanel extends JPanel {

    // cada setor do board que contém 9 espaços
    public SudokuPanel(final List<NumberText> sudokuTextFields){
        Dimension dimension = new Dimension(170,170);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setBorder(new LineBorder(Color.blue, 2, true));
        this.setVisible(true);
        sudokuTextFields.forEach(this::add);
    }
}
