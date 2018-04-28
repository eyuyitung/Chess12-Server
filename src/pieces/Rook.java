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

    private boolean inbetween = false;

    public Rook(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        System.out.println("ultra Troll");

        if (isValid(x, y, _x, _y)) {
            inbetween = false;
            // if(!(x != _x && y != _y)){
            System.out.println("ultra Troll");

            if(x != _x && y == _y){//horizontal
                if (x < _x && _x - x >= 2) {
                    for (int i = x + 1; i < _x; i++) {
                        if (b.getBoard()[i][y] != null) {
                            inbetween = true;
                        }
                    }
                } else if (x > _x && x - _x >= 2) {
                    for (int i = _x + 1; i < x; i++) {
                        if (b.getBoard()[i][y] != null) {
                            inbetween = true;
                        }
                    }
                }

                if (!inbetween) {
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                    b.getBoard()[x][y] = null;
                    x = _x;
                    System.out.println("big Troll");
                    return true;
                }
            }

            if(y != _y && x == _x) {//vertical
                if (y < _y && _y - y >= 2) {
                    for (int i = y + 1; i < _y; i++) {
                        if (b.getBoard()[x][i] != null) {
                            inbetween = true;
                        }
                    }
                } else if (y > _y && y - _y >= 2) {
                    for (int i = _y + 1; i < y; i++) {
                        if (b.getBoard()[x][i] != null) {
                            inbetween = true;
                        }
                    }
                }
                if (!inbetween){
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                    b.getBoard()[x][y] = null;
                    y = _y;
                    System.out.println("small Troll");
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "R ";
    }
}