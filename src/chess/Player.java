
package chess;

import java.util.ArrayList;
import pieces.Piece;


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
