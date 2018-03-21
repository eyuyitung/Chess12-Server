public class Pawn extends Piece{
    boolean firstMove = true;

    boolean canMove (){
        return board.spaceAhead(this.xPos, this.yPos) == null;
        if (firstMove)
            return board.spaceAhead(this.xPos,this.yPos + 1) == null;
    }

}
