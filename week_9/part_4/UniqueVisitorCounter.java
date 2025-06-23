package part_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class EmptyDataException extends DataProcessingException{
    public EmptyDataException(String message){
        super(message);
    }
}
public class UniqueVisitorCounter implements DataProcessor{
    @Override
    public List<String> process(List<String> rawData) throws DataProcessingException{
        if(rawData == null || rawData.isEmpty()){
            throw new EmptyDataException("No raw data to process! Did all tourists go missing?");
        }
        Set<String> uniqueNames = new HashSet<>();

        for(String entry : rawData){
            String name = extractName(entry);
            if(name!= null && !name.isEmpty()){
                uniqueNames.add(name.trim());
            }
        }
        return  Collections.singletonList("Unique Visitors: " + uniqueNames.size());
    }
     // Helper method to extract name from data string
   private String extractName(String entry) {
    String name = null;

    if (entry.startsWith("Visitor: ")) {
        int start = "Visitor: ".length();
        int end = entry.indexOf(",", start);
        if (end != -1) {
            name = entry.substring(start, end).trim();
        }
    } else if (entry.startsWith("Guest: ")) {
        int start = "Guest: ".length();
        int end = entry.indexOf(",", start);
        if (end != -1) {
            name = entry.substring(start, end).trim();
        }
    }

    return name;
}
// main to test
public static void main(String[] args) {
    DataProcessor processor = new UniqueVisitorCounter();

    // Case 1: Empty list
    try {
        List<String> emptyList = new ArrayList<>();
        processor.process(emptyList);
    } catch (DataProcessingException e) {
        System.out.println(" Error: " + e.getMessage());
    }

    //  Case 2: Valid list with some duplicate names
        try {
            List<String> visitorData = Arrays.asList(
            "Visitor: John Doe, USA",
            "Guest: Alice Smith, UK",
            "Visitor: John Doe, USA",
            "Guest: Ram Thapa, NP",
            "Visitor: Emily White, UK"
        );

        List<String> result = processor.process(visitorData);
        System.out.println(" Processing Result: " + result.get(0));
    } catch (DataProcessingException e) {
        System.out.println(" Error: " + e.getMessage());
        }
}
}
