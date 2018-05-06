package pieces;

import chess.Board;
import chess.Player;


public class Bishop extends Piece {

    private boolean inbetween = false;

    public Bishop(Player p, int x, int y) { super(p, x, y); }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b, x, y, _x, _y) && !isKing(b, _x, _y)) {
                checkInbetween(b, _x, _y);
            return !inbetween;
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

    private void checkInbetween(Board b, int targetX, int targetY) {
        inbetween = false;
        if (Math.abs(x - targetX) == Math.abs(y - targetY)) { //check if moving in diagonal line
            if (Math.abs(x - targetX) >= 2) {
                if (targetX < x && targetY < y) { //top left
                    for (int i = targetX + 1, j = targetY + 1; i < x; i++, j++)
                        if (b.getBoard()[i][j] != null)
                            inbetween = true;
                } else if (targetX > x && targetY < y) { //top right
                    for (int i = x + 1, j = y - 1; i < targetX; i++, j--)
                        if (b.getBoard()[i][j] != null)
                            inbetween = true;
                } else if (targetX < x && targetY > y) { //bottom left
                    for (int i = targetX + 1, j = targetY - 1; i < x; i++, j--)
                        if (b.getBoard()[i][j] != null)
                            inbetween = true;
                } else if (targetX > x && targetY > y) { //bottom right
                    for (int i = x + 1, j = y + 1; i < targetX; i++, j++)
                        if (b.getBoard()[i][j] != null)
                            inbetween = true;
                }
            }
        }
        else inbetween = true;
    }

    @Override
    public boolean check(Board b) {
        if (b.getPiece(x,y).p == b.white)
            checkInbetween(b, b.blackKingLocX, b.blackKingLocY);
        else
            checkInbetween(b, b.whiteKingLocX, b.whiteKingLocY);
        //*********************************
        return !inbetween;
    }

    @Override
    public String toString() {
        return "B ";
    }
}
