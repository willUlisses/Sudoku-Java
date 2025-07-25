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
}
