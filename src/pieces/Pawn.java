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

    @Override
    public boolean move(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y) && !isKing(b,_x,_y)) {
            if ((x == _x && y + movement == _y || !moved && y + 2 * movement == _y) && b.getBoard()[_x][_y] == null) {
                if(_y == 0 && movement == -1){
                    b.getBoard()[_x][_y] = new Queen(b.white, _x, 0);
                } else if(_y == 7 && movement == 1){
                    b.getBoard()[_x][_y] = new Queen(b.black, _x, 7);
                } else {
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                }
                b.getBoard()[x][y] = null;
                moved = true;
                x = _x;
                y = _y;
                return true;
            }
            else if (p.isWhite() && Math.abs(x - _x) == 1 && (y - _y) == 1 && b.getBoard()[_x][_y] != null) {
                if(_y == 0 && movement == -1){
                    b.getBoard()[_x][_y] = new Queen(b.white, _x, 0);
                } else if(_y == 7 && movement == 1){
                    b.getBoard()[_x][_y] = new Queen(b.black, _x, 7);
                } else {
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                }
                b.getBoard()[x][y] = null;
                moved = true;
                x = _x;
                y = _y;
                return true;
            }
            else if (!p.isWhite() && Math.abs(x - _x) == 1 && (y - _y) == -1 && b.getBoard()[_x][_y] != null) {
                if(_y == 0 && movement == -1){
                    b.getBoard()[_x][_y] = new Queen(b.white, _x, 0);
                } else if(_y == 7 && movement == 1){
                    b.getBoard()[_x][_y] = new Queen(b.black, _x, 7);
                } else {
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                }
                b.getBoard()[x][y] = null;
                moved = true;
                x = _x;
                y = _y;
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "P ";
    }
}
