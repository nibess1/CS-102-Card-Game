package Cards;

public class Queen extends Card{
    private boolean canMove;

    public Queen(int power, char picture, char symbol){
        super(power, picture, symbol);
        this.canMove = true;
    }

    @Override
    public boolean canMove(){
        return this.canMove;
    }

    public void hasMoved(){
        this.canMove = false;
    }
}
