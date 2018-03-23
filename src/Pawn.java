public class Pawn extends Piece{
    boolean firstMove = true;

    boolean canMove (){

        boolean able;
        if (board[xPos][yPos-1] == null)
            able = true;
        if (firstMove)
            if (board[xPos][yPos-2] == null)
                able = true;


    }

}
