
package chess;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

/**
 *
 * @author Lucas
 */
public class Board {

    Piece[][] board = new Piece[8][8];

    Player white;
    Player black;

    public Board(Player white, Player black) {
        this.white = white;
        this.black = black;

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

    public void printBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[j][i] != null) {
                    System.out.print(board[j][i]);
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

}
