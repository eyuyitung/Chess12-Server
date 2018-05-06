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
    public boolean moved;

    public Rook(Player p, int x, int y) {
        super(p, x, y);
        moved = false;
    }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y){
        if (isValid(x, y, _x, _y) && !sameColor(b,x,y,_x,_y) && !isKing(b,_x,_y)) {
            checkInbetween(b, _x, _y);
            return !inbetween;
        }
        return false;
    }


    @Override
    public void move(Board b, int _x, int _y) {
        if (checkValidMove(b,_x,_y)) {
            b.getBoard()[_x][_y] = b.getBoard()[x][y];
            b.getBoard()[x][y] = null;
            x = _x;
            y = _y;
            moved = true;
        }
    }

    private void checkInbetween(Board b, int targetX, int targetY){
        inbetween = false;
        if(x != targetX && y == targetY){//horizontal
            if (x < targetX && targetX - x >= 2) {
                for (int i = x + 1; i < targetX; i++) {
                    if (b.getBoard()[i][y] != null) {
                        inbetween = true;
                    }
                }
            } else if (x > targetX && x - targetX >= 2) {
                for (int i = targetX + 1; i < x; i++) {
                    if (b.getBoard()[i][y] != null) {
                        inbetween = true;
                    }
                }
            }
        }
        else if(y != targetY && x == targetX) {//vertical
            if (y < targetY && targetY - y >= 2) {
                for (int i = y + 1; i < targetY; i++) {
                    if (b.getBoard()[x][i] != null) {
                        inbetween = true;
                    }
                }
            } else if (y > targetY && y - targetY >= 2) {
                for (int i = targetY + 1; i < y; i++) {
                    if (b.getBoard()[x][i] != null) {
                        inbetween = true;
                    }
                }
            }
        }
        else
            inbetween = true;
    }

    @Override
    public boolean check(Board b) {
        if (b.getPiece(x,y).p == b.white)
            checkInbetween(b, b.blackKingLocX, b.blackKingLocY);
        else
            checkInbetween(b, b.whiteKingLocX, b.whiteKingLocY);
        //*********************************
            return !inbetween;
    }

        @Override
    public String toString() {
        return "R ";
    }
}