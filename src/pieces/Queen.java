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
    public Queen(Piece q) {
        super(q.p,q.x,q.y);
    }

    @Override
    public boolean checkValidMove(Board b, int _x, int _y) {
        if (isValid(x, y, _x, _y) && !sameColor(b, x, y, _x, _y) && !isKing(b, _x, _y)) {
            checkInbetween(b, _x, _y);
            return (!inbetween && (x != _x && y == _y || y != _y && x == _x || Math.abs(x - _x) == Math.abs(y - _y)));
        }
        return false;
    }

    @Override
    public void move(Board b, int _x, int _y, Piece piece) {
        if(checkValidMove(b, _x, _y)){

            piece = b.getPiece(_x,_y);  //*********

            b.getBoard()[_x][_y] = b.getBoard()[x][y];
            b.getBoard()[x][y] = null;
            x = _x;
            y = _y;
        }
    }

    private void checkInbetween(Board b, int targetX, int targetY) {
        inbetween = false;
        if (x != targetX && y == targetY) {//horizontal
            if (x < targetX && targetX - x >= 2) {
                for (int i = x + 1; i < targetX; i++) {
                    if (b.getPiece(i, y) != null) {
                        inbetween = true;
                    }
                }
            } else if (x > targetX && x - targetX >= 2) {
                for (int i = targetX + 1; i < x; i++) {
                    if (b.getPiece(i, y) != null) {
                        inbetween = true;
                    }
                }
            }
        } else if (y != targetY && x == targetX) {//vertical
            if (y < targetY && targetY - y >= 2) {
                for (int i = y + 1; i < targetY; i++) {
                    if (b.getPiece(x, i) != null) {
                        inbetween = true;
                    }
                }
            } else if (y > targetY && y - targetY >= 2) {
                for (int i = targetY + 1; i < y; i++) {
                    if (b.getPiece(x, i) != null) {
                        inbetween = true;
                    }
                }
            }
        } else if (Math.abs(x - targetX) == Math.abs(y - targetY)) { //check if moving in diagonal line
            if (Math.abs(x - targetX) >= 2) {
                if (targetX < x && targetY < y) { //top left
                    for (int i = targetX + 1, j = targetY + 1; i < x; i++, j++)
                        if (b.getPiece(i, j) != null)
                            inbetween = true;
                } else if (targetX > x && targetY < y) { //top right
                    for (int i = x + 1, j = y - 1; i < targetX; i++, j--)
                        if (b.getPiece(i, j) != null)
                            inbetween = true;
                } else if (targetX < x && targetY > y) { //bottom left
                    for (int i = targetX + 1, j = targetY - 1; i < x; i++, j--)
                        if (b.getPiece(i, j) != null)
                            inbetween = true;
                } else if (targetX > x && targetY > y) { //bottom right
                    for (int i = x + 1, j = y + 1; i < targetX; i++, j++)
                        if (b.getPiece(i, j) != null)
                            inbetween = true;
                }
            }
        }
        else inbetween = true;
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
        return "Q ";
    }
}
