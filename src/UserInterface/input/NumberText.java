package UserInterface.input;

import Model.Square;
import Service.EventE;
import Service.EventListener;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class NumberText extends JTextField implements EventListener {

    private final Square square;

    public NumberText(final Square square) {
        this.square = square;
        Dimension dimension = new Dimension(50,50);
        this.setSize(dimension);
        this.setPreferredSize(dimension);
        this.setVisible(true);
        this.setFont(new Font("Arial", Font.PLAIN, 20));
        this.setHorizontalAlignment(CENTER);
        this.setDocument(new NumberTextLimit());
        this.setEnabled(!square.isFixed());
        if (square.isFixed()){
            this.setText(square.getContent().toString());
        }
        this.getDocument().addDocumentListener(new DocumentListener() {

            private void changeSquareContent(){
                if (getText().isEmpty()){
                    square.clearSquare();
                    return;
                }
                square.setContent(Integer.parseInt(getText()));
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                changeSquareContent();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changeSquareContent();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changeSquareContent();
            }
        });
    }
    @Override
    public void update(final EventE event) {
        if (event.equals(EventE.CLEAR_SPACE) && this.isEnabled()) {
            this.setText("");
        }
    }
}
