
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
    int x = 0, y = 0;
    int fx = -1, fy = -1;// mouse coordinate values
    private int width = 800;
    private int height = 800;
    boolean first;
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
        first = false;
        frame.setSize(width, height);
        frame.getContentPane().add(drawing);
        frame.addMouseListener(new MouseListen());
        frame.setResizable(false);
        frame.setVisible(true);
    }

    private void initializePieces() {
        for (int i = 0; i < 8; i++) {
            board[i][1] = (new Pawn(white, i, 1));
        }
        board[0][0] = (new Rook(white, 0, 0));
        board[7][0] = (new Rook(white, 7, 0));
        board[2][0] = (new Bishop(white, 2, 0));
        board[5][0] = (new Bishop(white, 5, 0));
        board[1][0] = (new Knight(white, 1, 0));
        board[6][0] = (new Knight(white, 6, 0));
        board[3][0] = (new Queen(white, 3, 0));
        board[4][0] = (new King(white, 4, 0));
        for (int i = 0; i < 8; i++) {
            board[i][6] = (new Pawn(black, i, 6));
        }
        board[0][7] = (new Rook(black, 0, 7));
        board[7][7] = (new Rook(black, 7, 7));
        board[2][7] = (new Bishop(black, 2, 7));
        board[5][7] = (new Bishop(black, 5, 7));
        board[1][7] = (new Knight(black, 1, 7));
        board[6][7] = (new Knight(black, 6, 7));
        board[3][7] = (new Queen(black, 3, 7));
        board[4][7] = (new King(black, 4, 7));
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
        public void mouseReleased(MouseEvent e) {
            tClick = System.currentTimeMillis();
            x = e.getX() - 3;
            y = e.getY() - 25;
            drawing.repaint();


        }
    }

    public void mouseCoordinates() {

        int xb = width / 10;
        int yb = height / 10;

        if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9 && board[x / xb][y / yb] != null) {
            if (tClick > time) {
                time = System.currentTimeMillis() + 1000;
                if (first) {
                    fx = x / xb - 1;
                    fy = y / yb - 1;
                    System.out.println("from : " + fx + " " + fy);
                    first = false;
                } else {
                    try {
                        int _x = x / xb - 1;
                        int _y = y / yb - 1;
                        if (getPiece(fx, fy).isValid(fx, fy, _x, _y)) {
                            System.out.println("to : " + _x + " " + _y);
                            getPiece(fx, fy).move(this, _x, _y);
                            first = true;
                            drawing.repaint();
                        }
                    } catch (Exception e) {
                        System.out.println("invalid move");
                        first = false;

                    }

                }
            }
        }
    }




class Drawing extends JComponent {
    public Drawing() {
        repaint();
    }

    public void paint(Graphics g) {
        int xBorder = width / 10;
        int yBorder = height / 10;
        int arcSize = 10;
        char c;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i % 2 == 0) { // white top left
                    if (j % 2 != 0) {
                        g.setColor(Color.decode("#98dfe2"));
                        g.fillRoundRect(xBorder + i * (xBorder), yBorder + j * (yBorder), xBorder, yBorder, arcSize, arcSize);
                    }
                } else if (j % 2 == 0) {
                    g.setColor(Color.decode("#98dfe2"));
                    g.fillRoundRect(xBorder + i * (xBorder), yBorder + j * (yBorder), xBorder, yBorder, arcSize, arcSize);
                }
            }
        }
        g.setColor(Color.black);
        g.drawRect(xBorder, yBorder, xBorder * 8, yBorder * 8);

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != null) {
                    if (board[j][i].p.isWhite())
                        c = 'w';
                    else
                        c = 'b';
                    Image image = Textures.getImage(c, board[j][i].toString()).getImage();
                    Image newimg = image.getScaledInstance(xBorder, yBorder, java.awt.Image.SCALE_SMOOTH);
                    ImageIcon scaledIcon = new ImageIcon(newimg);
                    scaledIcon.paintIcon(this, g, xBorder + j * xBorder, yBorder + i * yBorder);
                }
            }
            // Textures.getImage('w', (board[j][i]).toString()).paintIcon(this, g, xBorder + j * xBorder, yBorder + i * yBorder);
        }
        g.drawRect(xBorder, yBorder, xBorder * 8, yBorder * 8);
        int yy = 0;
        int xx = 0;
        for (int row = yBorder; row < yBorder * 9; row += yBorder)
            g.drawString(Integer.toString(yy++), xBorder - 10, row + 30);
        for (int col = xBorder; col < xBorder * 9; col += xBorder)
            g.drawString(Integer.toString(xx++), col + 30, yBorder - 10);


        mouseCoordinates();
        System.out.println((x / xBorder - 1) + " " + (y / yBorder - 1));
        System.out.println(x + " " + y);
    }
}
}

