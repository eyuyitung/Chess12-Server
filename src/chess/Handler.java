
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
        while(true) {
            System.out.println("from");
            int x = Integer.parseInt(s.nextLine());
            int y = Integer.parseInt(s.nextLine());
            System.out.println("to");
            int _x = Integer.parseInt(s.nextLine());
            int _y = Integer.parseInt(s.nextLine());

            board.getPiece(x, y).move(board, _x, _y);
            board.display();
        }

    }

    public void close() { //close ports for networking

    }

}
