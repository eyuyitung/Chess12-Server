
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

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;

        frame.setSize(width, height);
        frame.setLayout(new GridLayout());
        frame.add(new Drawing());
        frame.setVisible(true);
        initializePieces();

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
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece getPiece(int x, int y) {
        return board[x][y];
    }

    class Drawing extends JComponent {
        public void paint(Graphics g) {
            int width = frame.getWidth();
            int height = frame.getHeight();
            int xBorder = width / 8;
            int yBorder = height / 8;
            int arcSize = 10;

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

            Textures.getImage('w','P').paintIcon(this, g, 100,100);

        }
    }
}

