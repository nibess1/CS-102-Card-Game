package locations;
import cards.*;
import exception.*;

import java.util.*;

public class SOE extends Location {

    public SOE(){
        super.setName("SOE");
        super.setDescription("The player with the lowest power wins.");
    }

    @Override
    public boolean playerWins() {
        return !super.playerWins();
    }

}
