package part_2;

// NavigationFailedException as a custom exception
class NavigationFailedException extends Exception{
    public NavigationFailedException(String message){
        super(message);
    }
}

public interface NavigationService {
    void navigate(String startPoint, String endPoint, RouteValidator validator) 
    throws NavigationFailedException;
} 