package pieces;

import chess.Board;
import chess.Player;

public class Knight extends Piece {

    public Knight(Player p, int x, int y) {
        super(p, x, y);
    }
    public Knight(Piece k) {
        super(k.p,k.x,k.y);
    }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y) && !isKing(b,_x,_y)) {
            if((Math.abs(x - _x) + Math.abs(y - _y)) == 3 && Math.abs(x - _x) == 2 * Math.abs(y - _y) || 2 * Math.abs(x - _x) == Math.abs(y - _y)) {
                return true;
            }
        }
        return false;
    }

    public void move(Board b, int _x, int _y, Piece piece) {
        if (checkValidMove(b, _x, _y)) {
            b.getBoard()[_x][_y] = b.getBoard()[x][y];
            b.getBoard()[x][y] = null;
            x = _x;
            y = _y;
        }
    }

    @Override
    public boolean check(Board b) {
        if (b.getPiece(x, y).p == b.white) {
            return ((Math.abs(x - b.blackKingLocX) + Math.abs(y - b.blackKingLocY)) == 3 && Math.abs(x - b.blackKingLocX) == 2 * Math.abs(y - b.blackKingLocY) || 2 * Math.abs(x - b.blackKingLocX) == Math.abs(y - b.blackKingLocY)) && Math.abs(x - b.blackKingLocX) + Math.abs(y - b.blackKingLocY) <= 3;
        } else {
            return ((Math.abs(x - b.whiteKingLocX) + Math.abs(y - b.whiteKingLocY)) == 3 && Math.abs(x - b.whiteKingLocX) == 2 * Math.abs(y - b.whiteKingLocY) || 2 * Math.abs(x - b.whiteKingLocX) == Math.abs(y - b.whiteKingLocY)) && Math.abs(x - b.whiteKingLocX) + Math.abs(y - b.whiteKingLocY) <= 3;
        }
    }

    @Override
    public String toString() {
        return "N ";
    }
}
