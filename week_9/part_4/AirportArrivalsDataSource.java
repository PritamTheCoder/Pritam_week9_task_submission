package part_4;

import java.util.ArrayList;
import java.util.List;

// Custom checked exception for simulated connection loss
class ConnectionLostException extends DataSourceAccessException {
    public ConnectionLostException(String message) {
        super(message);
    }
}

public class AirportArrivalsDataSource extends TouristDataSource{
   public AirportArrivalsDataSource(){
    super("Tribhuvan Airport Arrivals");
   } 
   @Override
    public List<String> fetchData() throws DataSourceAccessException{
        // Simulate connection loss with 30% probability
        if (sourceName.contains("Tribhuvan") && Math.random() < 0.3) {
            throw new ConnectionLostException("Airport data connection lost! Maybe a pigeon sat on the antenna?");
        }

        //  dummy visitor data
        List<String> arrivals = new ArrayList<>();
        arrivals.add("Visitor: John Doe, USA");
        arrivals.add("Visitor: Emily White, UK");
        arrivals.add("Visitor: Yuki Tanaka, Japan");
        return arrivals;
    }
    public static void main(String[] args) {
        TouristDataSource dataSource = new AirportArrivalsDataSource();

        try{
            List<String> visitors = dataSource.fetchData();
            System.out.println(" Airport Arrivals Data:");
            for (String visitor : visitors) {
                System.out.println(visitor);
            }
        }catch (DataSourceAccessException e){
             System.out.println(" Failed to fetch data: " + e.getMessage());
        }
    }
    }

