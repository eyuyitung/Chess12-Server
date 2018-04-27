package chess;
import javax.swing.*;
import java.awt.*;


public class Textures
{

    public static ImageIcon getImage (char c, char p){ // input in form w or b for color and toString for piece
        String localDir = System.getProperty("user.dir");
        ImageIcon piece = new ImageIcon(localDir + "\\Pieces\\"+ c + p +".png");
        return piece;

    }

}
