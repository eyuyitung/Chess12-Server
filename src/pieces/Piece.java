/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Board;
import chess.Player;
import java.awt.*;
import java.io.*;

/**
 *
 * @author Lucas
 */
public class Piece {

    protected boolean avalible; //change to taken out of the array later
    protected int x;
    protected int y;
    protected String dir = System.getProperty("user.dir");
    protected Image pieces = new File(dir + File.separator +"Pieces.png");
    public Piece(Player p, int x, int y) {
        avalible = true;
        this.x = x;
        this.y = y;
    }

    public boolean isValid(Board b, int fromX, int fromY, int toX, int toY) {
        if (toX == fromX && toY == fromY) {
            return false;
        } else if (toX < 0 || toX > 7 || fromX < 0 || fromX > 7 || toY < 0 || toY > 7 || fromY < 0 || fromY > 7) {
            return false;
        }
        return true;
    }
}
