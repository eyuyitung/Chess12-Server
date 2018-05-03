
package chess;

import java.io.IOException;
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
        boolean CurrentPlayer = true;
        while (true) {
            try {
                if (CurrentPlayer)
                    System.out.println("CurrentPlayer: White");
                else
                    System.out.println("CurrentPlayer: Black");
                System.out.println("From");
                int x = Integer.parseInt(s.nextLine());
                int y = Integer.parseInt(s.nextLine());
                System.out.println("To");
                int _x = Integer.parseInt(s.nextLine());
                int _y = Integer.parseInt(s.nextLine());
                if (CurrentPlayer == board.getPiece(x, y).p.white && board.getPiece(x, y).move(board, _x, _y)) {
                    CurrentPlayer = !CurrentPlayer;
                }
                board.display();
            } catch (Exception e) {
                System.out.println("invalid move.");
            }
        }

    }

    public void close() { //close ports for networking

    }

}
