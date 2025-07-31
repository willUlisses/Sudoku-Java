package UserInterface.screen;

import Model.GameStatusE;
import Service.BoardService;
import UserInterface.button.FinishGameButton;
import UserInterface.button.ResetGameButton;
import UserInterface.frame.MainFrame;
import UserInterface.panel.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class MainScreen {

    private final static Dimension DIMENSION = new Dimension(600,600);
    private final BoardService boardService;
    private JButton checkGameButton;

    public MainScreen(final Map<String, String> gamePositions) {
        this.boardService = new BoardService(gamePositions);
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(DIMENSION);
        JFrame mainFrame = new MainFrame(DIMENSION, mainPanel);
        addResetGameButton(mainPanel);
        addShowGameButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private void addFinishGameButton(JPanel mainPanel) {
        JButton finishGameButton =  new FinishGameButton(event -> {
            boolean hasErrors = boardService.hasErros();
            GameStatusE gameStatus = boardService.getGameStatus();
            String message = switch (gameStatus) {
                case NON_STARTED -> "O jogo ainda não foi iniciado";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está finalizado";
            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
        });
        mainPanel.add(finishGameButton);
    }

    private void addShowGameButton(JPanel mainPanel) {
        mainPanel.add(checkGameButton);
    }


    private void addResetGameButton(JPanel mainPanel) {
        JButton resetGameButton = new ResetGameButton(event -> {
            int dialogResult = JOptionPane.showConfirmDialog( //retorna um status 0 para confirmar, 1 para negar e -1 para fechar
                    // a caixa de dialog
                    null,
                    "Você realmente deseja reiniciar o jogo?",
                    "Limpar Jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (dialogResult == 0) {
                boardService.reset();
            }
        });
        mainPanel.add(resetGameButton);
    }

}
