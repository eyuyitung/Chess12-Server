/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.util.ArrayList;
import pieces.Piece;

/**
 *
 * @author Lucas
 */
public class Player {

    private ArrayList<Piece> capturedPieces;
    private boolean white;


    public Player(boolean b) {
        white = b;
    }

    public boolean isWhite() {
        return white;
    }

}
