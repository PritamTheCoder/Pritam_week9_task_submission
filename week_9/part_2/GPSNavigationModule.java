package part_2;

public class GPSNavigationModule implements NavigationService {
    @Override
    public void navigate(String startPoint, String endPoint, RouteValidator validator)
        throws NavigationFailedException {
        System.out.println("Attempting to navigate from " + startPoint + " to " + endPoint + "...");
        
        // Simulate GPS signal lost at Kalanki
        if (startPoint.equalsIgnoreCase("Kalanki")) {
            throw new NavigationFailedException("GPS signal lost near Kalanki! Welcome to Kathmandu traffic!");
        }

        try {
            double distance = simulatedDistance(startPoint,endPoint);
            boolean isValid = validator.isValidCommuteRoute(startPoint, endPoint, distance);

            if(isValid){
                System.out.println("Navigation successful! Estimated time: 20 minutes (or 2 hours depending on traffic).\n");
            }
        } catch (InvalidRouteException e) {
            throw new NavigationFailedException("Route validation failed!" + e);
        }
    }

        // healper method to simulate route distance
    private double simulatedDistance(String start,String end){
        return 5.0; // fixed value for testing
    }

    // main method for testing
    public static void main(String[] args) {
        RouteValidator validator = new KathmanduTrafficValidator();
        NavigationService gps = new GPSNavigationModule();

        // Case 1: Valid
        try {
            gps.navigate("Thamel", "Patan", validator);
        } catch (NavigationFailedException e) {
            System.out.println("NavigationFailedException: " + e.getMessage());
        }

        // Case 2: Same origin/destination
        try {
            gps.navigate("Baneshwor", "Baneshwor", validator);
        } catch (NavigationFailedException e) {
            System.out.println("NavigationFailedException: " + e.getMessage());
             System.out.println("Cause: " + e.getCause());
        }

        // Case 3: GPS lost at Kalanki
        try {
            gps.navigate("Kalanki", "Balaju", validator);
        } catch (NavigationFailedException e) {
            System.out.println("NavigationFailedException: " + e.getMessage());
        }
    }
}
