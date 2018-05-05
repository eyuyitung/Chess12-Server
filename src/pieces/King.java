package pieces;

import chess.Board;
import chess.Player;

public class King extends Piece {


    boolean moved;

    public King(Player p, int x, int y) {
        super(p, x, y);
        moved = false;
    }

    @Override
    public boolean move(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y) && !nearKing(b,x,y,_x,_y)) {
                if (Math.abs(x - _x) <= 1 && y == _y || Math.abs(y - _y) <= 1 && x == _x || Math.abs(x - _x) == 1 && Math.abs(y - _y) == 1) {
                    if(b.isWhite){
                        b.whiteKingLocX = _x;
                        b.whiteKingLocY = _y;

                    }
                    else{
                        b. blackKingLocX = _x;
                        b.blackKingLocY = _y;
                    }
                    System.out.println("White: " + b.whiteKingLocX + "," + b.whiteKingLocY);
                    System.out.println("Black: " + b.blackKingLocX + "," + b.blackKingLocY);
                    b.getBoard()[_x][_y] = b.getBoard()[x][y];
                    b.getBoard()[x][y] = null;
                    x = _x;
                    y = _y;
                    moved = true;
                    return true;
                }
            }
        return false;
    }

    public boolean nearKing(Board b, int x, int y, int _x, int _y) {

        for (int locX = -1; locX <= 1; locX++) {
            for (int locY = -1; locY <= 1; locY++) {

                if (b.getBoard()[x][y].p == b.white && (locX + _x == b.blackKingLocX && locY + _y == b.blackKingLocY)) {
                    return true;
                }
                else if (b.getBoard()[x][y].p == b.black && (locX + _x == b.whiteKingLocX && locY + _y == b.whiteKingLocY)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "K ";
    }
}
