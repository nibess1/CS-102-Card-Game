package locations;

public class SOE extends Location {

    public SOE(){
        super.setName("SOE");
        super.setDescription("The player with the lowest power wins.");
    }

    @Override
    public int playerWins() {
        if(super.playerWins() == 1){
            return 0;
        } else if (super.playerWins() == 0){
            return 1;
        } else{
            return 2;
        }
    }

}
