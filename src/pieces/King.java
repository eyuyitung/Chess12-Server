package pieces;

import chess.Board;
import chess.Player;

public class King extends Piece {

    boolean moved;
    boolean castle = false;

    public King(Player p, int x, int y) {
        super(p, x, y);
        moved = false;
    }

    public King(Piece k) {
        super(k.p,k.x,k.y);
        moved = false;
    }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b, x, y, _x, _y) && !nearKing(b, x, y, _x, _y)) {
            if (Math.abs(x - _x) <= 1 && y == _y || Math.abs(y - _y) <= 1 && x == _x || Math.abs(x - _x) == 1 && Math.abs(y - _y) == 1) {
                castle = false;
                return true;
            } else if (!moved && b.isWhite) {
                if (_x == 2 && checkCastle(b.getPiece(0, 7)) && b.getBoard()[1][7] == null && b.getBoard()[2][7] == null && b.getBoard()[3][7] == null) { //Queen
                    castle = true;
                    return true;
                } else if (_x == 6 && checkCastle(b.getPiece(7, 7)) && b.getBoard()[5][7] == null && b.getBoard()[6][7] == null) { //King side
                    castle = true;
                    return true;
                }
            } else if (!moved && !b.isWhite) {
                if (_x == 2 && checkCastle(b.getPiece(0, 0)) && b.getBoard()[1][0] == null && b.getBoard()[2][0] == null && b.getBoard()[3][0] == null) { //Queen Side
                    castle = true;
                    return true;
                } else if (_x == 6 && checkCastle(b.getPiece(7, 0)) && b.getBoard()[5][0] == null && b.getBoard()[6][0] == null) { //King side
                    castle = true;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void move(Board b, int _x, int _y, Piece piece) {
       if(checkValidMove(b,_x, _y)) {
           if (b.isWhite) {
               b.whiteKingLocX = _x;
               b.whiteKingLocY = _y;
           } else {
               b.blackKingLocX = _x;
               b.blackKingLocY = _y;
           }
       }

        piece = b.getPiece(_x,_y);  //*********

        b.getBoard()[_x][_y] = b.getBoard()[x][y];
        b.getBoard()[x][y] = null;
        b.getBoard()[_x][_y].x = _x;
        b.getBoard()[_x][_y].y = _y;
        moved = true;
        if (castle) {
            if (b.isWhite && _x == 2) {
                b.getBoard()[3][7] = b.getBoard()[0][7];
                b.getBoard()[0][7] = null;
                b.getBoard()[3][7].x = 3;
                b.getBoard()[3][7].y = 7;
            } else if (b.isWhite && _x == 6) {
                b.getBoard()[5][7] = b.getBoard()[7][7];
                b.getBoard()[7][7] = null;
                b.getBoard()[5][7].x = 5;
                b.getBoard()[5][7].y = 7;
            } else if (!b.isWhite && _x == 2) {
                b.getBoard()[3][0] = b.getBoard()[0][0];
                b.getBoard()[0][0] = null;
                b.getBoard()[3][0].x = 3;
                b.getBoard()[3][0].y = 0;
            } else if (!b.isWhite && _x == 6) {
                b.getBoard()[5][0] = b.getBoard()[7][0];
                b.getBoard()[7][0] = null;
                b.getBoard()[5][0].x = 5;
                b.getBoard()[5][0].y = 0;
            }
        }
    }

    public boolean checkCastle(Piece piece){
        if (piece instanceof Rook){
            Rook r = (Rook) piece;
            return !r.moved;
        }
        return false;
    }

    public boolean nearKing(Board b, int x, int y, int _x, int _y) {

        for (int locX = -1; locX <= 1; locX++) {
            for (int locY = -1; locY <= 1; locY++) {

                if (b.isWhite && (locX + _x == b.blackKingLocX && locY + _y == b.blackKingLocY)) {
                    return true;
                }
                else if (!b.isWhite && (locX + _x == b.whiteKingLocX && locY + _y == b.whiteKingLocY)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean check(Board b) {
        return false;
    }

    @Override
    public String toString() {
        return "K ";
    }
}
