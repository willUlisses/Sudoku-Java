package Model;

import java.util.Collection;
import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static Model.GameStatusE.*;

public class SudokuBoard {

    private final List<List<Square>> board;

    public SudokuBoard(final List<List<Square>> board) {
        this.board = board;
    }

    public List<List<Square>> getBoard() {
        return board;
    }

    public GameStatusE getStatus() {
        if (board.stream()
                .flatMap(Collection::stream)
                .noneMatch(s -> !s.isFixed() && nonNull(s.getContent()))){
            return NON_STARTED;
        }

        return board.stream()
                .flatMap(Collection::stream)
                .anyMatch(square -> !square.isFixed() && isNull(square.getContent())) ? INCOMPLETE : COMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == NON_STARTED) return false;

        return board.stream()
                .flatMap(Collection::stream)
                .anyMatch(square -> nonNull(square.getContent()) && !square.getContent().equals(square.getExpectedContent()));
    }

    public boolean clearValue(final int row, final int col) {
        Square square = board.get(row).get(col);
        if (square.isFixed()) return false;

        square.clearSquare();
        return true;
    }

    public boolean changeValue(final int row, final int col, final int value){
        Square squareChanging = board.get(row).get(col);
        if (squareChanging.isFixed()) return false;

        squareChanging.setContent(value);
        return true;
    }

    public void reset() {
        board.forEach(row -> row.forEach(Square::clearSquare)); //clear square utiliza setContent que verifica se o
        // espaço é fixo ou não então não é necessário utilizar o filter.
    }

    public boolean gameIsFinished() {
        return !hasErrors() && getStatus().equals(COMPLETE);
    }


}
