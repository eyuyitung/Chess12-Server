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
public abstract class Piece {

    protected int x;
    protected int y;

    public Piece(Player p, int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract boolean move(Board b, int _x, int _y);

    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (toX == fromX && toY == fromY) {
            return false;
        } else if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7) {
            return false;
        }
        return true;
    }
}
