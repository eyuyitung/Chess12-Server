package pieces;

import chess.Board;
import chess.Player;


public class Bishop extends Piece {

    private boolean inbetween = false;

    public Bishop(Player p, int x, int y) { super(p, x, y); }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b, x, y, _x, _y) && !isKing(b, _x, _y)) {
            inbetween = false;
            if (Math.abs(x - _x) == Math.abs(y - _y)) { //check if moving in diagonal line
                if (Math.abs(x - _x) >= 2) {
                    if (_x < x && _y < y) { //top left
                        for (int i = _x + 1, j = _y + 1; i < x; i++, j++)
                            if (b.getBoard()[i][j] != null)
                                inbetween = true;
                    } else if (_x > x && _y < y) { //top right
                        for (int i = x + 1, j = y - 1; i < _x; i++, j--)
                            if (b.getBoard()[i][j] != null)
                                inbetween = true;
                    } else if (_x < x && _y > y) { //bottom left
                        for (int i = _x + 1, j = _y - 1; i < x; i++, j--)
                            if (b.getBoard()[i][j] != null)
                                inbetween = true;
                    } else if (_x > x && _y > y) { //bottom right
                        for (int i = x + 1, j = y + 1; i < _x; i++, j++)
                            if (b.getBoard()[i][j] != null)
                                inbetween = true;
                    }
                }
                    return !inbetween;
            }
        }
        return false;
    }

    @Override
    public void move(Board b, int _x, int _y){
        if (checkValidMove(b, _x, _y)) {
            b.getBoard()[_x][_y] = b.getBoard()[x][y];
            b.getBoard()[x][y] = null;
            x = _x;
            y = _y;
        }
    }

    @Override
    public boolean check(Board b) {
        return false;
    }

    @Override
    public String toString() {
        return "B ";
    }
}
