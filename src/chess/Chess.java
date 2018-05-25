
package chess;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Chess {


    private static Handler h;

    public static void main(String[] args) {

        h = new Handler();
        int screen = 1;

        h.menu();


        while (true) {
            if(screen == 1) {
                h.menuDisplay();
            }

            else if (screen == 2) {
                h.frame.setVisible(false);
                h.frame.dispose();
                h.run(); //change to game loop later
                h.close(); //closes all the ports
            }
            if(h.clickPlayLocal)
                screen = 2;
            h.drawing.repaint();
        }
    }

}
