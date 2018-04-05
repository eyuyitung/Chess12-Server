
package chess;

/**
 *
 * @author Lucas
 */
public class Chess {
    
    private static Handler h = new Handler();
    
    public static void main(String[] args) {
        h.run();
        h.close(); //closes all the ports
    }
    
}
