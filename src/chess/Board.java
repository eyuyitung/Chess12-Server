
package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

import java.awt.*;
import javax.swing.*;


public class Board {

    Piece[][] board = new Piece[8][8];

    Player white;
    Player black;

    int width = 900;
    int height = 750;
    JFrame frame = new JFrame("chess");
    Drawing drawing = new Drawing();

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;

        initFrame();
        initializePieces();

    }

    public void initFrame() {

        frame.setSize(width, height);
        frame.setLayout(new GridLayout());
        frame.add(drawing);
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
        drawing.repaint();
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    class Drawing extends JComponent {
        public Drawing() {
            repaint();
        }

        public void paint(Graphics g) {
            int width = frame.getWidth();
            int height = frame.getHeight();
            int xBorder = width / 10;
            int yBorder = height / 10;
            int arcSize = 10;
            char c;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (i % 2 == 0) { // white top left
                        if (j % 2 == 0) {
                            g.setColor(Color.white);
                            g.drawRoundRect(xBorder + i * (width - 2 * xBorder) / 8, yBorder + j * (height - 2 * yBorder) / 8, (width - 2 * xBorder) / 8, (height - 2 * yBorder) / 8, arcSize, arcSize);
                        } else {
                            g.setColor(Color.decode("#98dfe2"));
                            g.fillRoundRect(xBorder + i * (width - 2 * xBorder) / 8, yBorder + j * (height - 2 * yBorder) / 8, (width - 2 * xBorder) / 8, (height - 2 * yBorder) / 8, arcSize, arcSize);
                        }
                    } else if (j % 2 == 0) {
                        g.setColor(Color.decode("#98dfe2"));
                        g.fillRoundRect(xBorder + i * (width - 2 * xBorder) / 8, yBorder + j * (height - 2 * yBorder) / 8, (width - 2 * xBorder) / 8, (height - 2 * yBorder) / 8, arcSize, arcSize);
                    } else {
                        g.setColor(Color.white);
                        g.drawRoundRect(xBorder + i * (width - 2 * xBorder) / 8, yBorder + j * (height - 2 * yBorder) / 8, (width - 2 * xBorder) / 8, (height - 2 * yBorder) / 8, arcSize, arcSize);
                    }
                }
            }
            g.setColor(Color.black);
            g.drawRect(xBorder, yBorder, width - 2 * xBorder, height - 2 * yBorder);
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
        }
    }
}


