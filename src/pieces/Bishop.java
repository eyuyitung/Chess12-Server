package pieces;

import chess.Board;
import chess.Player;


public class Bishop extends Piece {

    public Bishop(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        if (x + 0 == _x && y == _y) {
            x = _x;
            y = _y;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "B ";
    }
}
