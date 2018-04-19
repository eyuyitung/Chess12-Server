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
public class King extends Piece {

    public King(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y)) {
            if (x + 1 == _x || x - 1 == _x || y + 1 == _y || y - 1 == _y) {          
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                x = _x;
                y = _y;
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
