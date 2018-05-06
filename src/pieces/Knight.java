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

    public boolean move(Board b, int _x, int _y) {
        //System.out.println("ultra Troll");
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y) && !isKing(b,_x,_y)) {
            //System.out.println("ultra Troll");
            if((Math.abs(x - _x) + Math.abs(y - _y)) == 3 && Math.abs(x - _x) == 2 * Math.abs(y - _y) || 2 * Math.abs(x - _x) == Math.abs(y - _y)) {
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                x = _x;
                y = _y;
               // System.out.println("big Troll");
                return true;
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
        return "N ";
    }
}
