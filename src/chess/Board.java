
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

    Timer timer;

    int timedLength;
    int counter = 0;
    int wT = 0;
    int bT = 0;

    //public double wTotalTime, bTotalTime;
    //private long wStartTime;
    //private long bStartTime, bEndTime, wEndTime;
    //private double duration;

    int fx = -1, fy = -1;// mouse coordinate values
    private int width = 800;
    private int height = 800;
    private int xb = width / 10;
    private int yb = height / 10;

    private JFrame frame = new JFrame("Chess");
    private Drawing drawing = new Drawing();
    public boolean isWhite = true;
    public int whiteKingLocX;
    public int whiteKingLocY;
    public int blackKingLocX;
    public int blackKingLocY;

    public boolean whiteKingChecked;
    public boolean blackKingChecked;
    boolean first = true;

    public Piece capturedPiece = null;

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;
        //initFrame();
        initializePieces();
    }

    public void initFrame() {
        frame.setSize(width, height);
        frame.getContentPane().add(drawing);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addMouseListener(new MouseListen());
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
      /*  for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != null) {
                    System.out.print(board[j][i]);
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }*/
        initFrame();
        //wStartTime = System.currentTimeMillis();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter ++;
                if(timedLength != 0)
                System.out.println("counter = " + counter + " s");
                drawing.repaint();
                //timer.stop();
            }
        });
        timer.start();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    class MouseListen extends MouseAdapter {

        int px, py;
        int ix = -1;
        int iy = -1;

        int dx = -1, dy = -1;

        @Override
        public void mousePressed(MouseEvent e) {
            px = e.getX();
            py = e.getY();
            if (px >= xb && px <= xb * 9 && py >= yb && py <= yb * 9) {
                fx = (px - 3) / xb - 1;
                fy = (py - 25) / yb - 1;
                if (board[fx][fy] != null || !first) {
                    System.out.println("From : " + fx + " " + fy);
                } else
                    fx = fy = -1;
            } else {
                fx = fy = -1;
            }
            System.out.println("lol");
            drawing.repaint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                dx = (x - 3) / xb - 1;
                dy = (y - 25) / yb - 1;

                if (!(fx == dx && fy == dy)) { // drag movement
                    mouseMove(fx, fy, dx, dy);
                    first = true;
                } else { // click movement
                    if (first)  {
                        ix = fx;
                        iy = fy;
                        first = false;
                    } else {
                        mouseMove(ix, iy, dx, dy);
                        first = true;
                    }
                }
            } else {
                fx = fy = dx = dy = -1;
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
                            System.out.println("white check black "+getPiece(_x, _y).check(this));
                            //wEndTime = System.currentTimeMillis();
                            //duration = (wEndTime - wStartTime)/1000.0;
                            //System.out.println(wTotalTime);
                            //wTotalTime -= duration;
                            //System.out.println("white took " + duration + " s");
                            //System.out.println("white has " + wTotalTime + " s left");
                            timer.stop();
                            wT += counter;
                            System.out.println("white uses " + wT + " s in total");
                            counter = 0;
                            isWhite = !isWhite;
                            //bStartTime = System.currentTimeMillis();
                            timer.start();
                        }
                    }

                } else {
                    if (!getPiece(x, y).p.isWhite()) {
                        if (getPiece(x, y).checkValidMove(this, _x, _y)) {
                            getPiece(x, y).move(this, _x, _y, capturedPiece);
                            System.out.println("black check white "+ getPiece(_x, _y).check(this));
                            //bEndTime = System.currentTimeMillis();
                            //duration = (bEndTime - bStartTime)/1000.0;
                            //bTotalTime -= duration;
                            //System.out.println("black took " + duration + " s");
                            //System.out.println("black has " + wTotalTime + " s left");
                            timer.stop();
                            bT += counter;
                            System.out.println("black uses " + bT + " s in total");
                            counter = 0;
                            isWhite = !isWhite;
                            timer.start();
                            //wStartTime = System.currentTimeMillis();
                        }
                    }
                }
                System.out.println("To : " + _x + " " + _y);
            }
        } catch (Exception e) {
            System.out.println("Invalid move");
        }
        drawing.repaint();
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
            Font main = new Font ("Serif", Font.BOLD, 20);
            Font sec = new Font ("Serif", Font.PLAIN, 14);
            Font gui = new Font ("Serif", Font.PLAIN, 12);

            if(timedLength != 0) {
                if (isWhite) {
                    g.setFont(main);
                    g.drawString(String.valueOf((timedLength - (wT + counter)) / 60 + " : " + ((300 - wT - counter) % 60)), 100, 30);
                    g.setFont(sec);
                    g.drawString(String.valueOf((timedLength - bT) / 60 + " : " + (300 - wT) % 60), 670, 30);
                } else if (!isWhite) {
                    g.setFont(sec);
                    g.drawString(String.valueOf((timedLength - wT) / 60 + " : " + (300 - wT) % 60), 100, 30);
                    g.setFont(main);
                    g.drawString(String.valueOf((timedLength - (bT + counter)) / 60 + " : " + ((300 - bT - counter) % 60)), 670, 30);
                }
            }
            g.setFont(gui);

            int arcSize = 10;
            char c;
            for (int i = 0; i < 8; i++) { // drawing board squares
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
            for (int i = 0; i < 8; i++) { // drawing highlighted square of selected piece
                for (int j = 0; j < 8; j++) {
                    if (i == fx && j == fy) { // bad workaround for square remaining highlighted after turn
                        if (board[fx][fy] != null && board[fx][fy].p.isWhite() && isWhite && first) {
                            g.setColor(Color.decode("#ff8c00"));
                            g.fillRoundRect(xb + i * (xb), yb + j * (yb), xb, yb, arcSize, arcSize);
                        } else if (board[fx][fy] != null && !board[fx][fy].p.isWhite() && !isWhite && first) {
                            g.setColor(Color.decode("#ff8c00"));
                            g.fillRoundRect(xb + i * (xb), yb + j * (yb), xb, yb, arcSize, arcSize);
                        }
                    }
                }
            }


            for (int i = 0; i < 8; i++) {// drawing pieces
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
            g.setColor(Color.black); // drawing board border
            g.drawRect(xb, yb, xb * 8, yb * 8);
            int ly = 0;
            int lx = 0;
            for (
                    int row = yb;
                    row < yb * 9; row += yb)
                g.drawString(Integer.toString(ly++), xb - 10, row + 30);
            for (
                    int col = xb;
                    col < xb * 9; col += xb)
                g.drawString(Integer.toString(lx++), col + 30, yb - 10);
            if (isWhite)
                g.drawString("White", xb, yb);
            else
                g.drawString("Black", xb, yb);

        }
    }
}

