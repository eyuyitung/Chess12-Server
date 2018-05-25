
package chess;

import java.util.Scanner;


public class Handler {

    Player white;
    Player black;

    Board board;

    Scanner s = new Scanner(System.in);

    public Handler() {
        white = new Player(true);
        black = new Player(false);
        board = new Board(white, black);
    }

    public void run() { //change to tick / render
        board.display();
        board.initFrame();
        Music.volume = Music.Volume.LOW;
        Music.MII.play();
        if (false) { //SPEED CHESS MUSIC
            Music.MII.stop();
            Music.SONIC.play();

        }
    }

    public void close() { //close ports for networking

    }

}
