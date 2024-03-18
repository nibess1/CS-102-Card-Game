package Exception;

public class LocationRejectionException extends RuntimeException{
    
    public LocationRejectionException(){
        throw new RuntimeException("General Exception");
    }

    public LocationRejectionException(String message){
        throw new RuntimeException(message);
    }
}
