<<<<<<< HEAD
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOE extends Location {

    public SOE(){
        super.setName("SOE");
        super.setDescription("The card with the lowest power wins.");
    }

    @Override
    public boolean playerWins() {
        return !super.playerWins();
    }

}
=======
package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class SOE extends Location {

    public SOE(){
        super.setName("SOE");
        super.setDescription("The card with the lowest power wins.");
    }

    @Override
    public boolean playerWins() {
        return !super.playerWins();
    }

}
>>>>>>> b0473c83d9d50c428405d2c94d81eb5396175578
