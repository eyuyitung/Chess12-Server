
package chess;

import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Handler {

    Player white;
    Player black;

    Board board;
    boolean clicked = false;
    private int width = 1200;
    private int height = 800;
    JFrame frame = new JFrame("Chess");
    Drawing drawing = new Drawing();

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
                if (CurrentPlayer == board.getPiece(x, y).p.white && board.getPiece(x, y).checkValidMove(board, _x, _y)) {
                    board.getPiece(x, y).move(board, _x, _y, board.capturedPiece);
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

    public void menu(){
        frame.setSize(width, height);
        frame.getContentPane().add(drawing);
        frame.addMouseListener(new MouseListen());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

    }

    public void menuDisplay(){
        frame.setVisible(true);
    }

    public void clickStarted(){}

    class MouseListen extends MouseAdapter {
        private int fx = -1, fy = -1;// mouse coordinate values
        private int dx = -1, dy = -1;
        public int cx, cy, px, py, rx, ry;

        public void mouseClicked(MouseEvent e) {
            cx = e.getX();
            cy = e.getY();
            System.out.println("hey");
            clicked = true;
        }

        public void mousePressed(MouseEvent e) {
            px = e.getX();
            py = e.getY();
        }

        public void mouseReleased(MouseEvent e) {
            rx = e.getX();
            ry = e.getY();

        }

        public void moveHandler(int x, int y) { }
        }


    class Drawing extends JComponent {
        public Drawing() {
            repaint();
        }

        public void paint(Graphics g) {
            String localDir = System.getProperty("user.dir");
            ImageIcon bg = new ImageIcon(localDir + "\\bg.jpg");

            Image image = bg.getImage();
            Image newimg = image.getScaledInstance(1200, 800, java.awt.Image.SCALE_SMOOTH);
            ImageIcon sBg = new ImageIcon(newimg);

            sBg.paintIcon(this, g, 0,0);

            //System.out.println(localDir);
        }
    }
}


