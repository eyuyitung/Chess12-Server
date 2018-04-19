
package chess;

/**
 *
 * @author Lucas
 */
public class Chess {

    private static Handler h;

    public static void main(String[] args) {
        h = new Handler();
        h.run(); //change to game loop later
        h.close(); //closes all the ports
    }

}
