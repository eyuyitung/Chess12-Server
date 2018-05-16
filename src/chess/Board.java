
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

    public Player white;
    public Player black;

    private int width = 800;
    private int height = 800;
    private int xb = width / 10;
    private int yb = height / 10;
    private boolean first = true;
    private JFrame frame = new JFrame("Chess");
    private Drawing drawing = new Drawing();
    public boolean isWhite = true;
    public int whiteKingLocX;
    public int whiteKingLocY;
    public int blackKingLocX;
    public int blackKingLocY;


    public Piece capturedPiece = null;

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
        whiteKingLocX = 4;
        whiteKingLocY = 7;
        blackKingLocX = 4;
        blackKingLocY = 0;

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
        private int fx = -1, fy = -1;// mouse coordinate values
        private int dx = -1, dy = -1;
        public int cx, cy, px, py, rx, ry;

        public void mouseClicked(MouseEvent e) {
            System.out.println("click");
            cx = e.getX();
            cy = e.getY();
        }

        public void mousePressed(MouseEvent e) {
            px = e.getX();
            py = e.getY();
            /*
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                fx = (x - 3) / xb - 1;
                fy = (y - 25) / yb - 1;
                if (board[fx][fy] != null) {
                    System.out.println("From : " + fx + " " + fy);
                } else
                    fx = fy = -1;
            } else {
                fx = fy = -1;
            }*/
        }

        public void mouseReleased(MouseEvent e) {
            rx = e.getX();
            ry = e.getY();
            if (px == rx && py == ry) { // click
                moveHandler(cx, cy);
            } else {
                moveHandler(px, py);
                moveHandler(rx, ry);
            }
            drawing.repaint();
                /*
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                dx = (x - 3) / xb - 1;
                dy = (y - 25) / yb - 1;
                mouseMove(fx, fy, dx, dy);
                drawing.repaint();
            } else {
                fx = fy = dx = dy = -1;
            }*/
        }

        public void moveHandler(int x, int y) {
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                if (first) {
                    System.out.println("click 1");
                    fx = (x - 3) / xb - 1;
                    fy = (y - 25) / yb - 1;
                    if (board[fx][fy] != null) {
                        System.out.println("From : " + fx + " " + fy);
                        first = false;
                    } else
                        fx = fy = -1;
                } else {
                    System.out.println("click 2");
                    dx = (x - 3) / xb - 1;
                    dy = (y - 25) / yb - 1;
                    mouseMove(fx, fy, dx, dy);
                    drawing.repaint();
                    first = true;
                }

            }
        }
    }


    public void mouseMove(int x, int y, int _x, int _y) {

        try {

            if (!(x == _x && y == _y)) {
                if (isWhite) {
                    if (getPiece(x, y).p.isWhite()) {
                        if (getPiece(x, y).checkValidMove(this, _x, _y)) {
                            getPiece(x, y).move(this, _x, _y, capturedPiece);
                            System.out.println("white checking black " + getPiece(_x, _y).check(this));
                            if (checkCheck())
                                getPiece(_x, _y).move(this, x, y, capturedPiece);
                            else {
                                isWhite = !isWhite;
                                System.out.println("white turn complete");
                            }
                        }
                    }
                } else {
                    if (!getPiece(x, y).p.isWhite()) {
                        if (getPiece(x, y).checkValidMove(this, _x, _y)) {
                            getPiece(x, y).move(this, _x, _y, capturedPiece);
                            System.out.println("black checking white " + getPiece(_x, _y).check(this));
                            if (checkCheck())
                                getPiece(_x, _y).move(this, x, y, capturedPiece);
                            else {
                                isWhite = !isWhite;
                                System.out.println("black turn complete");
                            }
                        }
                    }
                }
                System.out.println(checkCheck());
                System.out.println("To : " + _x + " " + _y);
            }
        } catch (Exception e) {
            System.out.println("Invalid move");
        }
    }

    public boolean checkCheck() {  //returns true if in check
        boolean tmp = false;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (isWhite) {
                    if (getPiece(j, i) != null && !getPiece(j, i).p.isWhite()) {
                        if (getPiece(j, i).check(this)) {
                            tmp = true;
                        }
                    }
                } else {
                    if (getPiece(j, i) != null && getPiece(j, i).p.isWhite()) {
                        if (getPiece(j, i).check(this))
                            tmp = true;
                    }
                }
            }
        }
        return tmp;
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
            }
            g.drawRect(xb, yb, xb * 8, yb * 8);
            int ly = 0;
            int lx = 0;
            for (int row = yb; row < yb * 9; row += yb)
                g.drawString(Integer.toString(ly++), xb - 10, row + 30);
            for (int col = xb; col < xb * 9; col += xb)
                g.drawString(Integer.toString(lx++), col + 30, yb - 10);
            if (isWhite)
                g.drawString("White", xb, yb);
            else
                g.drawString("Black", xb, yb);
        }
    }
}

