package Model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Objects.nonNull;

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
            return GameStatusE.NON_STARTED;
        } else if (board.stream()
                .flatMap(Collection::stream)
                .allMatch(s-> !s.isFixed() && nonNull(s.getContent()))){
            return GameStatusE.COMPLETE;
        } else
            return GameStatusE.INCOMPLETE;
    }

    public boolean hasErrors() {
        if (getStatus() == GameStatusE.NON_STARTED) return false;

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
        board.stream()
                .flatMap(Collection::stream)
                .filter(square -> !square.isFixed())
                .forEach(Square::clearSquare);
    }
}
