package part_4;

import java.util.List;

// Custom checked exception for data processing errors
class DataProcessingException extends Exception{
    public DataProcessingException(String message){
        super(message);
    }
}
// Interface for processing raw data
public interface DataProcessor {
abstract List<String> process(List<String> rawData) throws DataProcessingException;
} 
