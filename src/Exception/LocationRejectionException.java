package exception;

public class LocationRejectionException extends RuntimeException{
    private String message;
    
    public LocationRejectionException(){
        throw new RuntimeException("General Exception");
    }

    public LocationRejectionException(String message){
        this.message = message;
    }

    public void getErrorMessage(){
        System.out.println(this.message);
    }
}
