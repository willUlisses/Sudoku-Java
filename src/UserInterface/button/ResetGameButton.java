package UserInterface.button;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResetGameButton extends JButton {

    public ResetGameButton(final ActionListener actionListener){
        this.setText("Reiniciar Jogo");
        this.addActionListener(actionListener);
    }
}
