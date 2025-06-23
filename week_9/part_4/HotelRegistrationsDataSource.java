package part_4;

import java.util.ArrayList;
import java.util.List;

// Custom checked exception for authentication failure
class AuthenticationFailedException extends DataSourceAccessException {
    public AuthenticationFailedException(String message) {
        super(message);
    }
}

public class HotelRegistrationsDataSource extends TouristDataSource{
    public HotelRegistrationsDataSource(){
        super("Kathmandu Hotels Registrations");
    }
     @Override
    public List<String> fetchData() throws DataSourceAccessException {
      // Simulate authentication failure with 20% chance
        if (sourceName.contains("Hotels") && Math.random() < 0.2) {
            throw new AuthenticationFailedException("Hotel API authentication failed! Did someone forget the password again?");
        }
        // Dummy hotel registration data
        List<String> registrations = new ArrayList<>();
        registrations.add("Hotel: Yak & Yeti, Guest: Ram Thapa, NP");
        registrations.add("Hotel: Annapurna, Guest: Alice Smith, AU");
        registrations.add("Hotel: Dwarika's, Guest: Zhang Wei, CN");
        return registrations;
    }
    // main
    public static void main(String[] args) {
        TouristDataSource dataSource = new HotelRegistrationsDataSource();

        try{
            List<String> hotelGuests = dataSource.fetchData();
            System.out.println(" Hotel Registrations Data:");
            for(String guest : hotelGuests){ // polymorphism
                System.out.println(guest);
            }
        }catch(DataSourceAccessException e){
            System.out.println(" Failed to fetch hotel data: " + e.getMessage());
        }
    }
}