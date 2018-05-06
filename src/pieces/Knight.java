/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Board;
import chess.Player;

/**
 *
 * @author Lucas
 */
public class Knight extends Piece {

    public Knight(Player p, int x, int y) {
        super(p, x, y);
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

    public void move(Board b, int _x, int _y) {
        if (checkValidMove(b, _x, _y)) {
            b.getBoard()[_x][_y] = b.getBoard()[x][y];
            b.getBoard()[x][y] = null;
            x = _x;
            y = _y;
        }
    }

    @Override
    public boolean check(Board b) {
        if (b.getPiece(x,y).p == b.white){
            return (Math.abs(x - b.blackKingLocX) + Math.abs(y - b.blackKingLocY)) == 3 && Math.abs(x - b.blackKingLocX) == 2 * Math.abs(y - b.blackKingLocY) || 2 * Math.abs(x - b.blackKingLocX) == Math.abs(y - b.blackKingLocY);
        }
        else
            return (Math.abs(x - b.whiteKingLocX) + Math.abs(y - b.whiteKingLocY)) == 3 && Math.abs(x - b.whiteKingLocX) == 2 * Math.abs(y - b.whiteKingLocY) || 2 * Math.abs(x - b.whiteKingLocX) == Math.abs(y - b.whiteKingLocY);
    }

    @Override
    public String toString() {
        return "N ";
    }
}
