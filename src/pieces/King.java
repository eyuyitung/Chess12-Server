package pieces;

import chess.Board;
import chess.Player;

public class King extends Piece {

    private boolean moved;

    public King(Player p, int x, int y) {
        super(p, x, y);
        moved = false;
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y)) {
            if (Math.abs(x - _x) <= 1 && y == _y || Math.abs(y - _y) <= 1 && x == _x || Math.abs(x - _x) == 1 && Math.abs(y - _y) == 1) {
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                x = _x;
                y = _y;
                moved = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "K ";
    }
}
