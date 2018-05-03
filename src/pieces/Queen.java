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
public class Queen extends Piece {

    private boolean inbetween = false;

    public Queen(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        System.out.println("ultra Troll");

        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y)) {
            inbetween = false;

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
            }
            else if(y != _y && x == _x) {//vertical
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
            }
            else if(Math.abs(x - _x)== Math.abs(y - _y)) { //check if moving in diagonal line
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
            }
            if (!inbetween && (x != _x && y == _y || y != _y && x == _x || Math.abs(x - _x)== Math.abs(y - _y))) {
                b.getBoard()[_x][_y] = b.getBoard()[x][y];
                b.getBoard()[x][y] = null;
                x = _x;
                y = _y;
                System.out.println("big Troll");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Q ";
    }
}
