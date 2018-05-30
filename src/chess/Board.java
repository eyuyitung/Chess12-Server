
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
import java.io.*;
import java.net.*;
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
    public boolean lan;
    boolean initLan = true;
    Socket sock = null;
    public int fx = -1, fy = -1;// mouse coordinate values
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
    boolean first = true;
    public Piece capturedPiece = null;

    PrintStream out = null;
    ServerSocket servsock;

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
        initFrame();
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                counter++;
                if (timedLength != 0)
                    System.out.println("counter = " + counter + " s");
                if (lan)
                    try {
                        lanPlay();
                    } catch (IOException ioe) {
                    }
                drawing.repaint();
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

        int ix = -1;
        int iy = -1;

        int dx = -1, dy = -1;

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            if (x >= xb && x <= xb * 9 && y >= yb && y <= yb * 9) {
                fx = (x - 3) / xb - 1;
                fy = (y - 25) / yb - 1;
                if (lan) {
                    if (!board[fx][fy].p.isWhite())
                        fx = fy = -1;
                }

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
                    if (first) {
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

    public void lanPlay() throws IOException {

        if (initLan) {
            System.out.println("Starting server ...");
            servsock = new ServerSocket(4444, 0);
            sock = servsock.accept();
            System.out.println("sock = " + sock);
        }
        out = new PrintStream(sock.getOutputStream());
        if (initLan) {
            System.out.println("timedLength = " + timedLength);
            if (timedLength != 0)
                out.println(timedLength); // first move sends timedLength to client
            out.flush();
            initLan = false;
        }

        BufferedReader in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        if (!isWhite) {
            System.out.println("waiting for response");
            if (in.ready()) {
                int x = Integer.parseInt(in.readLine());
                int y = Integer.parseInt(in.readLine());
                int _x = Integer.parseInt(in.readLine());
                int _y = Integer.parseInt(in.readLine());
                if (timedLength != 0) {
                    wT = Integer.parseInt(in.readLine());
                    //bT = Integer.parseInt(in.readLine());

                }
                System.out.println("from " + x + ", " + y);
                System.out.println("to " + _x + ", " + _y);
                System.out.println("black has " + bT + " s remaining");
                mouseMove(x, y, _x, _y);

            }
        }

    }


    public void mouseMove(int x, int y, int _x, int _y) {

        Piece[][] tmp = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j] != null) {
                    if (board[i][j] instanceof King) {
                        tmp[i][j] = new King(board[i][j]);
                    } else if (board[i][j] instanceof Queen) {
                        tmp[i][j] = new Queen(board[i][j]);
                    } else if (board[i][j] instanceof Bishop) {
                        tmp[i][j] = new Bishop(board[i][j]);
                    } else if (board[i][j] instanceof Knight) {
                        tmp[i][j] = new Knight(board[i][j]);
                    } else if (board[i][j] instanceof Rook) {
                        tmp[i][j] = new Rook(board[i][j]);
                    } else if (board[i][j] instanceof Pawn) {
                        tmp[i][j] = new Pawn(board[i][j]);
                    }

                }
            }
        }

        try {
            if (!(x == _x && y == _y)) {
                if (isWhite) {
                    if (getPiece(x, y).p.isWhite()) {
                        if (getPiece(x, y).checkValidMove(this, _x, _y)) {
                            getPiece(x, y).move(this, _x, _y, capturedPiece);
                            System.out.println("white check black " + getPiece(_x, _y).check(this));

                            if (timedLength != 0) {
                                timer.stop();
                                wT += counter;
                                System.out.println("white uses " + wT + " s in total");
                                counter = 0;
                                timer.start();
                            }
                            if (lan) {
                                out.println(x);
                                out.println(y);
                                out.println(_x);
                                out.println(_y);
                                if (timedLength != 0) {
                                    //out.println(wT);
                                    out.println(bT);

                                }
                                out.flush();
                            }
                            isWhite = !isWhite;
                        }
                    }

                } else {

                    if (!getPiece(x, y).p.isWhite()) {

                        if (getPiece(x, y).checkValidMove(this, _x, _y)) {
                            getPiece(x, y).move(this, _x, _y, capturedPiece);
                            System.out.println("black check white " + getPiece(_x, _y).check(this));
                            if (timedLength != 0) {
                                timer.stop();
                                bT += counter;
                                System.out.println("black uses " + bT + " s in total");
                                counter = 0;
                                timer.start();
                            }
                            isWhite = !isWhite;
                        }
                    }
                }
                boolean checked = false;
                //checks for the checking
                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (getPiece(i, j) != null && getPiece(i, j).p.white == isWhite && !checked) {
                            checked = getPiece(i, j).check(this);
                            if (checked) {
                                System.out.println("x " + getPiece(i, j).x + " y " + getPiece(i, j).y);

                            }
                        }
                    }
                }
                if (checked) {
                    board = tmp;
                    isWhite = !isWhite;
                    System.out.println("same");
                }
            }
            System.out.println("To : " + _x + " " + _y);
        } catch (Exception e) {
            System.out.println("Invalid move");
        }
        drawing.repaint();
    }


    class Drawing extends JComponent {
        public Drawing() {
            repaint();
        }

        public void paint(Graphics g) {
            Font main = new Font("Serif", Font.BOLD, 20);
            Font sec = new Font("Serif", Font.PLAIN, 14);
            Font gui = new Font("Serif", Font.PLAIN, 12);

            if (timedLength != 0) {
                if (isWhite) {
                    g.setFont(main);
                    g.drawString(String.valueOf((timedLength - (wT + counter)) / 60 + " : " + ((300 - wT - counter) % 60)), 100, 30);
                    g.setFont(sec);
                    g.drawString(String.valueOf((timedLength - bT) / 60 + " : " + (300 - bT) % 60), 670, 30);
                } else {
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


            for (int i = 0; i < 8; i++) {// drawing pieces
                for (int j = 0; j < 8; j++) {
                    Piece cPiece = board[j][i];
                    if (cPiece != null) {
                        if (cPiece.selected(Board.this)) { // drawing selection highlight
                            g.setColor(Color.decode("#ff8c00"));
                            g.fillRoundRect(xb + j * (xb), yb + i * (yb), xb, yb, arcSize, arcSize);
                        }
                        if (cPiece.p.isWhite())
                            c = 'w';
                        else
                            c = 'b';
                        Image image = Textures.getImage(c, cPiece.toString()).getImage();
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

