/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Player;

/**
 *
 * @author Lucas
 */
public class Queen extends Piece {

    public Queen(Player p, int x, int y) {
        super(p, x, y);
    }

    @Override
    public String toString() {
        return "Q";
    }
}
