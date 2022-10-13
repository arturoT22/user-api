package cl.ionix.user.exception;

public class UsersDatabaseIsEmptyException extends Exception{
    public UsersDatabaseIsEmptyException(String message){
        super(message);
    }
}
