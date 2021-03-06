
package pieces;

import chess.Board;
import chess.Player;

public abstract class Piece {

    public int x;
    public int y;
    public Player p;

    public Piece(Player p, int x, int y) {
        this.p = p;
        this.x = x;
        this.y = y;
    }

    public Piece(Piece piece) {
        if (p != null) {
            this.p = piece.p;
            this.x = piece.x;
            this.y = piece.y;
        }
    }

    public abstract boolean checkValidMove(Board b, int _x, int _y);

    public abstract void move(Board b, int _x, int _y, Piece piece);

    public abstract boolean check(Board b);



    public boolean isValid(int fromX, int fromY, int toX, int toY) {
        if (toX == fromX && toY == fromY) {
            return false;
        } else if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7) {
            return false;
        }
        return true;
    }

    public boolean sameColor(Board b, int x, int y, int _x, int _y){
        if (b.getBoard()[_x][_y] != null)
            return (b.getBoard()[x][y].p == b.getBoard()[_x][_y].p);
        return false;
    }


    public boolean isKing(Board b, int _x, int _y){
        if (b.getBoard()[_x][_y] != null)
            return (b.getBoard()[_x][_y] instanceof King);
        return false;
    }
    public boolean selected(Board b){
        return(b.fx == this.x && b.fy == this.y && p.isWhite() == b.isWhite);
    }

}
