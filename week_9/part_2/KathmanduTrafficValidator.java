// Kathmandu Traffic Route Validator
package part_2;

// custom checked exception for SameLocationException as a sub-exception for InvalidRouteException
class SameLocationException extends InvalidRouteException{ 
    public SameLocationException(String message){
        super(message);
    }
}
public class KathmanduTrafficValidator implements RouteValidator {
   
    @Override
    public boolean isValidCommuteRoute(String origin, String destination, double distanceKm)
    throws InvalidRouteException{
           if (origin.equalsIgnoreCase(destination)){
            throw new SameLocationException("Origin and destination cannot be the same! Are you just spinning in circles, Damodar?");
           }
           if (distanceKm < 0.1 || distanceKm > 30){
            throw new InvalidRouteException("Distance " + distanceKm + "km is unrealistic for Kathmandu commute!");
           }
           return true;
    }
    // main
    public static void main(String[] args) {
        KathmanduTrafficValidator validator = new KathmanduTrafficValidator();

        // Test case 1: Same Origin and destination
        try {
            validator.isValidCommuteRoute("Baneshwor", "Baneshwor", 2); // same location
        } catch (SameLocationException e) {
            System.out.println("SameLocationException: " + e.getMessage());
        } catch (InvalidRouteException e) {
            System.out.println("InvalidRouteException: " + e.getMessage());
        }

        // Test Case 2: Distance too short
        try {
            validator.isValidCommuteRoute("Koteshwor", "Thapagaun", 0.05);
        } catch (InvalidRouteException e) {
            System.out.println("InvalidRouteException: " + e.getMessage());
        }

        // Test Case 3: Distance too long
        try {
            validator.isValidCommuteRoute("Kalanki", "Dhulikhel", 40);
        } catch (InvalidRouteException e) {
            System.out.println("InvalidRouteException: " + e.getMessage());
        }

        // Test Case 4: Valid route
         try {
            boolean result = validator.isValidCommuteRoute("Thamel", "Patan", 5);
            if (result) {
                System.out.println("Route is valid!");
            }
        } catch (InvalidRouteException e) {
            System.out.println("InvalidRouteException: " + e.getMessage());
        }
    }
}
