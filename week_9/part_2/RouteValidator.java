// Route Validity Checker Interface
package part_2;

// Custom exception for invalid routes
class InvalidRouteException extends Exception {
    public InvalidRouteException(String message) {
        super(message);
    }
}

// Interface for route validation
public interface RouteValidator {
    boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
    throws InvalidRouteException;
}