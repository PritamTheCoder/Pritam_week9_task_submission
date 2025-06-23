package part_4;

import java.util.ArrayList;
import java.util.List;

public class TourismReportGenerator {
    // Static method to generate the overall report
    static void generateOverallReport(List<TouristDataSource> dataSources, DataProcessor processor) {
        System.out.println("Generating overall tourism report...");
        // Loop through each data source
        for (TouristDataSource dataSource : dataSources) {
            List<String> rawData = null;

            // Fetch data
            try {
                rawData = dataSource.fetchData();
            } catch (DataSourceAccessException e) {
                System.out.println("Could not fetch data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this source.");
                if (e.getCause() != null) {
                    System.out.println(" Reason: " + e.getCause().getMessage());
                }
                System.out.println(" Data handling from " + dataSource.sourceName + " completed.\n");
                continue; // move to next data source
            }

            // Process data
            try {
                List<String> result = processor.process(rawData);
                for (String output : result) {
                    System.out.println(" Report from " + dataSource.sourceName + ": " + output);
                }
            } catch (DataProcessingException e) {
                System.out.println(" Error processing data from " + dataSource.sourceName + ": " + e.getMessage() + ". Skipping this data.");
            } finally {
                System.out.println(" Data handling from " + dataSource.sourceName + " completed.\n");
            }
        }

        System.out.println("Overall tourism report generation completed.");
    }
// main
    public static void main(String[] args) {
        // Prepare data sources
        List<TouristDataSource> sources = new ArrayList<>();
        sources.add(new AirportArrivalsDataSource());
        sources.add(new HotelRegistrationsDataSource());

        DataProcessor processor = new UniqueVisitorCounter();

            // Generate the report
        generateOverallReport(sources, processor);
    }
}
