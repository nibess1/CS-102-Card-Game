package locations;

public class SOE extends Location {

    public SOE(){
        super.setName("SOE");
        super.setDescription("The player with the lowest power wins.");
    }

    @Override
    public int getWinningPlayer() {  
        if(super.getWinningPlayer() == 0){
            return 0;
        }
        return 3 - super.getWinningPlayer();
    }

}
