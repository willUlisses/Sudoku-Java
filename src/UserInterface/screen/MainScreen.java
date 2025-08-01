package UserInterface.screen;

import Model.GameStatusE;
import Model.Square;
import Service.BoardService;
import Service.EventE;
import Service.NotifierService;
import UserInterface.button.CheckGameStatusButton;
import UserInterface.button.FinishGameButton;
import UserInterface.button.ResetGameButton;
import UserInterface.frame.MainFrame;
import UserInterface.input.NumberText;
import UserInterface.panel.MainPanel;
import UserInterface.panel.SudokuPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainScreen {

    private final static Dimension DIMENSION = new Dimension(600,600);

    private final BoardService boardService;
    private final NotifierService notifierService;

    private JButton finishGameButton;
    private JButton checkGameButton;
    private JButton resetGameButton;

    public MainScreen(final Map<String, String> gamePositions) {
        this.boardService = new BoardService(gamePositions);
        this.notifierService = new NotifierService();
    }

    public void buildMainScreen() {
        JPanel mainPanel = new MainPanel(DIMENSION);
        JFrame mainFrame = new MainFrame(DIMENSION, mainPanel);
        for (int r = 0; r < 9; r+=3) {
            int endRow = r + 2;
            for (int c = 0; c < 9; c+=3){
                int endCol = c + 2;
                List<Square> squaresOfSector = getSquaresFromSector(boardService.getPositions(), c, endCol, r, endRow);
                JPanel sector = generateSudokuSector(squaresOfSector);
                mainPanel.add(sector);
            }
        }
        addResetGameButton(mainPanel);
        addCheckGameButton(mainPanel);
        addFinishGameButton(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    private List<Square> getSquaresFromSector(final List<List<Square>> gamePositions, final int initCol,
                                              final int endCol, final int initRow, final int endRow) {
        List<Square> squaresFromSector = new ArrayList<>();
        for (int r = initRow; r <= endRow; r++) {
            for (int c = initCol; c <= endCol; c++) {
                squaresFromSector.add(gamePositions.get(r).get(c));
            }
        }
        return squaresFromSector;
    }

    private JPanel generateSudokuSector(final List<Square> squaresOfSudokuSector) {
        List<NumberText> numberTextFields = new ArrayList<>(squaresOfSudokuSector.stream().map(NumberText::new).toList());
        numberTextFields.forEach(textField -> notifierService.subscribe(EventE.CLEAR_SPACE, textField));
        return new SudokuPanel(numberTextFields);
    }


    private void addFinishGameButton(JPanel mainPanel) {
        finishGameButton =  new FinishGameButton(event ->{
            if (boardService.gameIsFinished()){
                JOptionPane.showMessageDialog(null, "Parabéns, o jogo foi finalizado!");
                resetGameButton.setEnabled(false);
                checkGameButton.setEnabled(false);
                finishGameButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Seu jogo tem algum erro, ajuste e tente novamente.");
            }
        });
        mainPanel.add(finishGameButton);
    }


    private void addCheckGameButton(JPanel mainPanel) {
        checkGameButton = new CheckGameStatusButton(event -> {
            boolean hasErrors = boardService.hasErros();
            GameStatusE gameStatus = boardService.getGameStatus();
            String message = switch (gameStatus) {
                case NON_STARTED -> "O jogo ainda não foi iniciado";
                case INCOMPLETE -> "O jogo está incompleto";
                case COMPLETE -> "O jogo está finalizado";
            };
            message += hasErrors ? " e contém erros" : " e não contém erros";
            JOptionPane.showMessageDialog(null, message);
        });
        mainPanel.add(checkGameButton);
    }


    private void addResetGameButton(JPanel mainPanel) {
        resetGameButton = new ResetGameButton(event -> {
            int dialogResult = JOptionPane.showConfirmDialog( //retorna um status 0 para confirmar, 1 para negar e -1 para fechar
                    // a caixa de dialog
                    null,
                    "Você realmente deseja reiniciar o jogo?",
                    "Limpar Jogo",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
            );
            if (dialogResult == JOptionPane.YES_OPTION) {
                boardService.reset();
                notifierService.notify(EventE.CLEAR_SPACE);
            }
        });
        mainPanel.add(resetGameButton);
    }

}
