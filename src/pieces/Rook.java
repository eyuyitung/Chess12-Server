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
public class Rook extends Piece {

    public Rook(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        System.out.println("ultra Troll");

        if (isValid(x, y, _x, _y)) {
            // if(!(x != _x && y != _y)){
            System.out.println("ultra Troll");
            if(x != _x && y == _y){//horizontal
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                x = _x;
                System.out.println("big Troll");
                return true;
            }
            if(y != _y && x == _x){//vertical
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                y = _y;
                System.out.println("small Troll");
                return true;
                //   }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "R ";
    }
}