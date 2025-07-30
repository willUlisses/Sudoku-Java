package Service;

import Model.GameStatusE;
import Model.Square;
import Model.SudokuBoard;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BoardService {
    private final static int BOARD_LIMIT = 9;
    private final SudokuBoard board;

    public BoardService(final Map<String, String> gamePositions){
        this.board = new SudokuBoard(initBoard(gamePositions));
    }

    public List<List<Square>> getPositions() {
        return this.board.getBoard();
    }

    public void reset() {
        this.board.reset();
    }

    public boolean hasErros() {
        return this.board.hasErrors();
    }

    public GameStatusE getGameStatus() {
        return this.board.getStatus();
    }

    public boolean gameIsFinished() {
        return this.board.gameIsFinished();
    }

    private List<List<Square>> initBoard(final Map<String, String> gamePositions) {
        List<List<Square>> squares = new ArrayList<>();
        for (int i = 0; i < BOARD_LIMIT; i++) {
            squares.add(new ArrayList<>());
            for (int j = 0; j < BOARD_LIMIT; j++){
                String squareConfig = gamePositions.get("%s,%s".formatted(i,j));
                int expectedContent = Integer.parseInt(squareConfig.split(",")[0]);
                boolean squareStatus = Boolean.parseBoolean(squareConfig.split(",")[1]);
                Square square = new Square(expectedContent, squareStatus);
                squares.get(i).add(square);
            }
        }
        return squares;
    }

}
