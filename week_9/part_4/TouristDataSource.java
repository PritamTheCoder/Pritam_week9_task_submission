package part_4;

import java.util.List;

class DataSourceAccessException extends Exception{
    public DataSourceAccessException(String message){
        super(message);
    }
}
abstract class TouristDataSource {
    protected String sourceName;
    public TouristDataSource(String sourceName){
        this.sourceName = sourceName;
    }
    // abstract method
    public abstract List<String> fetchData() throws DataSourceAccessException;
}
