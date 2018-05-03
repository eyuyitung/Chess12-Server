
package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Board {

    Piece[][] board = new Piece[8][8];

    Player white;
    Player black;

    int fx = -1, fy = -1;// mouse coordinate values
    int dx = -1, dy = -1;
    private int width = 800;
    private int height = 800;
    int xb = width / 10;
    int yb = height / 10;
    long tClick;
    long time = 0;
    JFrame frame = new JFrame("Chess");
    Drawing drawing = new Drawing();

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;
        initFrame();
        initializePieces();
    }

    public void initFrame() {
        frame.setSize(width, height);
        frame.getContentPane().add(drawing);
        frame.addMouseListener(new MouseListen());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initializePieces() {
        for (int i = 0; i < 8; i++) {
            board[i][1] = (new Pawn(black, i, 1));
        }
        board[0][0] = (new Rook(black, 0, 0));
        board[7][0] = (new Rook(black, 7, 0));
        board[2][0] = (new Bishop(black, 2, 0));
        board[5][0] = (new Bishop(black, 5, 0));
        board[1][0] = (new Knight(black, 1, 0));
        board[6][0] = (new Knight(black, 6, 0));
        board[3][0] = (new Queen(black, 3, 0));
        board[4][0] = (new King(black, 4, 0));
        for (int i = 0; i < 8; i++) {
            board[i][6] = (new Pawn(white, i, 6));
        }
        board[0][7] = (new Rook(white, 0, 7));
        board[7][7] = (new Rook(white, 7, 7));
        board[2][7] = (new Bishop(white, 2, 7));
        board[5][7] = (new Bishop(white, 5, 7));
        board[1][7] = (new Knight(white, 1, 7));
        board[6][7] = (new Knight(white, 6, 7));
        board[3][7] = (new Queen(white, 3, 7));
        board[4][7] = (new King(white, 4, 7));
    }

    public void display() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != null) {
                    System.out.print(board[j][i]);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
        // drawing.repaint();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    class MouseListen extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                fx = (x - 3) / xb - 1;
                fy = (y - 25) / yb - 1;
                if (board[fx][fy] != null) {
                    System.out.println("from : " + fx + " " + fy);
                }
                else
                    fx = fy = -1;
            } else {

                fx = fy = -1;
            }


        }

        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                dx = (x - 3) / xb - 1;
                dy = (y - 25) / yb - 1;
                System.out.println("to : " + dx + " " + dy);
                mouseMove();
                drawing.repaint();
            } else {

                fx = fy = dx = dy = -1;
            }
        }
    }

    public void mouseMove() {

        try {
                System.out.println("to : " + dx + " " + dy);
                getPiece(fx, fy).move(this, dx, dy);

        } catch (Exception e) {
            System.out.println("invalid move");


        }
    }


    class Drawing extends JComponent {
        public Drawing() {
            repaint();
        }

        public void paint(Graphics g) {
            int arcSize = 10;
            char c;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i % 2 == 0) { // white top left
                        if (j % 2 != 0) {
                            g.setColor(Color.decode("#98dfe2"));
                            g.fillRoundRect(xb + i * (xb), yb + j * (yb), xb, yb, arcSize, arcSize);
                        }
                    } else if (j % 2 == 0) {
                        g.setColor(Color.decode("#98dfe2"));
                        g.fillRoundRect(xb + i * (xb), yb + j * (yb), xb, yb, arcSize, arcSize);
                    }
                }
            }
            g.setColor(Color.black);
            g.drawRect(xb, yb, xb * 8, yb * 8);

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[j][i] != null) {
                        if (board[j][i].p.isWhite())
                            c = 'w';
                        else
                            c = 'b';
                        Image image = Textures.getImage(c, board[j][i].toString()).getImage();
                        Image newimg = image.getScaledInstance(xb, yb, java.awt.Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(newimg);
                        scaledIcon.paintIcon(this, g, xb + j * xb, yb + i * yb);
                    }
                }
                // Textures.getImage('w', (board[j][i]).toString()).paintIcon(this, g, xBorder + j * xBorder, yBorder + i * yBorder);
            }
            g.drawRect(xb, yb, xb * 8, yb * 8);
            int ly = 0;
            int lx = 0;
            for (int row = yb; row < yb * 9; row += yb)
                g.drawString(Integer.toString(ly++), xb - 10, row + 30);
            for (int col = xb; col < xb * 9; col += xb)
                g.drawString(Integer.toString(lx++), col + 30, yb - 10);
        }
    }
}

