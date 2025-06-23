package part_2;

public class CommutePlanner {

    // Static method to plan the commute
    public static void planMyCommute(String origin, String destination,RouteValidator validator, NavigationService navigator) {
        System.out.println("Planning your commute from " + origin + " to " + destination + "...");

        try {
            navigator.navigate(origin, destination, validator);
        } catch (NavigationFailedException e) {
            Throwable cause = e.getCause();

            if (cause instanceof SameLocationException) {
                System.out.println("Cannot plan: Origin and destination are the same!");
            } else if (cause instanceof InvalidRouteException) {
                System.out.println("Cannot plan: Invalid route details - " + cause.getMessage());
            } else {
                System.out.println("Cannot plan: GPS issue or unexpected error - " + e.getMessage());
            }
        } finally {
            System.out.println("Commute planning for " + origin + " to " + destination + " completed.");
            System.out.println("-----------------------------------------------------");
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        RouteValidator validator = new KathmanduTrafficValidator();
        NavigationService gps = new GPSNavigationModule();

        // Case 1: Same origin/destination (SameLocationException)
        planMyCommute("Baneshwor", "Baneshwor", validator, gps);

        // Case 2: Valid route
        planMyCommute("Thamel", "Patan", validator, gps);

        // Case 3: GPS signal lost
        planMyCommute("Kalanki", "Balaju", validator, gps);

        // Case 4: Distance too long (handled internally by simulatedDistance = 5.0, so this is just an extra)
        planMyCommute("Koteshwor", "Dhulikhel", validator, gps);
    }
}
