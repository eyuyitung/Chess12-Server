public class Piece extends Board{
    int xPos;
    int yPos;
    boolean isWhite;

    void select (){
        highlight spaces can move to
    }
    void move (){
        change position to selection
    }
    boolean checkIfDead() {
        check if piece is actually taken before removing
    }
    boolean checkCheck() {
        check if checking the king
    }
    boolean canMove (){
        if (!checkCheck)

            return able;
    }
