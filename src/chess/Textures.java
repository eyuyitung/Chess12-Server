package chess;
import javax.swing.*;
import java.awt.*;


public class Textures
{

    public static ImageIcon getImage (char color, String piece){ // input in form w or b for color and toString for piece
        String localDir = System.getProperty("user.dir");
        ImageIcon p = new ImageIcon(localDir + "\\Pieces\\"+ color + piece.trim() +".png");
        return p;

    }

}
