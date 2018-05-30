
package pieces;

import chess.Board;
import chess.Player;


public class Pawn extends Piece {

    private int movement;
    private boolean moved;

    public Pawn(Player p, int x, int y) {
        super(p, x, y);
        if (p.isWhite()) {
            movement = -1;
        } else {
            movement = 1;
        }
        moved = false;
    }
    public Pawn(Piece pawn) {
        super(pawn.p,pawn.x,pawn.y);
        if (p.isWhite()) {
            movement = -1;
        } else {
            movement = 1;
        }
        moved = false;
    }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b, x, y, _x, _y) && !isKing(b, _x, _y)) {
/*
            if(b.isWhite && b.whiteKingChecked || !b.isWhite && b.blackKingChecked){
                return false;
            }
            */
            if ((x == _x && (y + movement == _y || !moved && y + 2 * movement == _y)) && b.getBoard()[_x][_y] == null) {    //Normal move
                return true;
            } else if (p.isWhite() && Math.abs(x - _x) == 1 && (y - _y) == 1 && b.getBoard()[_x][_y] != null) {   //White Pawn Capture
                return true;
            } else if (!p.isWhite() && Math.abs(x - _x) == 1 && (y - _y) == -1 && b.getBoard()[_x][_y] != null) {   //Black Pawn Capture
                return true;
            }
        }
        return false;
    }

    @Override
    public void move(Board b, int _x, int _y, Piece piece) {
        if (checkValidMove(b, _x, _y)) {

            piece = b.getPiece(_x,_y);  //*********

            if (_y == 0 && movement == -1) {
                b.getBoard()[_x][_y] = new Queen(b.white, _x, 0);
            } else if (_y == 7 && movement == 1) {
                b.getBoard()[_x][_y] = new Queen(b.black, _x, 7);
            } else {
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
            }
            b.getBoard()[x][y] = null;
            moved = true;
            x = _x;
            y = _y;
        }
    }

    @Override
    public boolean check(Board b) {
        if (b.getPiece(x, y).p == b.white && b.blackKingLocY == y - 1 && (x - 1 == b.blackKingLocX || x + 1 == b.blackKingLocX)) {
            //b.blackKingChecked = true;
            return true;
        } else if (b.getPiece(x, y).p == b.black && b.whiteKingLocY == y + 1 && (x - 1 == b.whiteKingLocX || x + 1 == b.whiteKingLocX)) {
            //b.whiteKingChecked = true;
            return true;
        } else {
            return false;
        }

    }


    @Override
    public String toString() {
        return "P ";
    }
}
