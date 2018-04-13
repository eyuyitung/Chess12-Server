
package pieces;

import chess.Board;
import chess.Player;
import java.awt.*;
import java.io.*;
import javax.swing.*;


public class Piece {

    protected boolean avalible; //change to taken out of the array later
    protected int x;
    protected int y;
    protected ImageIcon pieces = new ImageIcon(System.getProperty("user.dir") + File.separator +"Pieces.png");
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

    public void paint (Graphics g) {

    }
}
