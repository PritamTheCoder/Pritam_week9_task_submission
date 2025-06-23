// Deusi Bhailo Event Planning Module
package part_1;

import java.util.ArrayList;
import java.util.List;

class NoRouteException extends FestivalPlanningException{
    public NoRouteException(String message){
    super(message);
    }
}

public class DeusiBhailo extends Activity{
    private List<String> plannedRoutes;
    private int numberOfPerformers;
    public DeusiBhailo(double estimatedCost,List<String> plannedRoutes, int numberOfPerformers){
        super("Deusi Bhailo Program", estimatedCost);
        this.plannedRoutes = plannedRoutes;
        this.numberOfPerformers = numberOfPerformers;
    }

    @Override
    public void planActivity() throws FestivalPlanningException {
        if(plannedRoutes == null || plannedRoutes.isEmpty()){
            throw new NoRouteException("No routes planned for Deusi Bhailo! Are we just singing in the living room?");
        }
        else if (numberOfPerformers < 3){
            throw new NoRouteException("Need at least 3 performers for a proper Deusi Bhailo!");
        }else {
            System.out.println("Deusi Bhailo program with " + numberOfPerformers + " performers planned for " + plannedRoutes.size() + " routes!");
        }
    }
public static void main(String[] args) {
    List<String> validRoutes = new ArrayList<>();
    validRoutes.add("House A");
    validRoutes.add("House B");

    List<String> emptyRoutes = new ArrayList<>();
    DeusiBhailo[] events = {
        new DeusiBhailo(1000, validRoutes, 5), // this is valid
        new DeusiBhailo(1200, emptyRoutes, 4), // empty route
        new DeusiBhailo(800, validRoutes, 2), // not enough performers
        new DeusiBhailo(900, emptyRoutes, 2)
    };

    for (DeusiBhailo event : events){
        event.displayOverview();
        try {
            event.planActivity();
        } catch (NoRouteException e) {
            System.out.println("No Route Exception : "+ e.getMessage());
        } catch (FestivalPlanningException e){
            System.out.println("Festival Planning Exception : "+ e.getMessage());
        }
        System.out.println("---------");
        }
    }
}
