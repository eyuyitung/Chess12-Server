/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

/**
 *
 * @author Lucas
 */
public class Handler {

    Player white;
    Player black;

    Board board;

    public Handler() {
        white = new Player();
        black = new Player();

        board = new Board(white, black);

    }
    
    public void run() {
        
    }
    
    public void close() {
        
    }

}
