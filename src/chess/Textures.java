package chess;
import javax.swing.*;


public class Textures
{
    /*** getImage *******************************************
     * Purpose: return chess piece images as imageicons     *
     * Parameters: color - char identifier of piece color   *
     *             piece - letter specifying piece          *
     * Returns: ImageIcon of desired piece                  *
     ******************************************************/
    public static ImageIcon getImage (char color, String piece){ // input in form w or b for color and toString for piece
        String localDir = System.getProperty("user.dir");
        ImageIcon p = new ImageIcon(localDir + "\\Pieces\\"+ color + piece.trim() +".png");
        return p;

    }

}
