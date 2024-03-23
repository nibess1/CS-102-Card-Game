package Locations;
import Cards.*;
import Exception.*;
import java.util.*;

public class Admin extends Location {
    public Admin() {
        super.setName("Admin Building");
        super.setDescription("You can only place one card here");
    }


    @Override
    public boolean isAvailable(boolean p1){
        if (checkDestroyed()) {
            throw new LocationRejectionException("Location is destroyed!");
        }
        if(p1){
            if(getP1NumLiveCards() == 0){
                return true;
            }
        } else{
            if(getP2NumLiveCards() == 0){
                return true;
            }
        }
        return false;
    }
}
