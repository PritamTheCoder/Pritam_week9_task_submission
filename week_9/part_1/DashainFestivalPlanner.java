// Dashain Festival Master Planner
package part_1;

import java.util.ArrayList;
import java.util.List;

public class DashainFestivalPlanner {
    // Static method to execute all activities
    public static void executeFestivalPlan(List<Activity> activities){
        for(Activity activity : activities){
            activity.displayOverview();
            try{
                activity.planActivity();
            }catch (InvalidGuestCountException e){
                System.out.println("Planning Warning (Guests): " + e.getMessage());
            }catch(BudgetExceedException e){
                System.out.println("Planning Warning (Budget): " + e.getMessage());
            }catch(NoRouteException e){
                System.out.println("Planning Warning (Route): "+ e.getMessage());
            }catch(FestivalPlanningException e){
                System.out.println("General Planning Error: " + e.getMessage());
            }finally{
                System.out.println("Activity planning attempt for " + activity.activityName + " completed.");
                System.out.println("-------------------------------------------------------------------");
            }
        }
    }
    public static void main(String[] args) {
        // Tika ceremony instances
        TikaCeremony tika1 = new TikaCeremony(30000, 10, "Grandfather");
        TikaCeremony tika2 = new TikaCeremony(70000, 8, "Uncle");         // BudgetExceeded
        TikaCeremony tika3 = new TikaCeremony(20000, 3, "Aunt");          // InvalidGuestCount
    
        // Deusi Bhailo instances
        List<String> routes1 = List.of("House A","House B");
        List<String> routesEmpty = new ArrayList<>();

        DeusiBhailo deusi1 = new DeusiBhailo(1500, routes1, 5);          // Valid
        DeusiBhailo deusi2 = new DeusiBhailo(1800, routesEmpty, 4);      // NoRouteException
        DeusiBhailo deusi3 = new DeusiBhailo(1000, routes1, 2);          // Performer count low
    
        // Add all activities to the festival list
        List<Activity> activities = new ArrayList<>();
        activities.add(tika1);
        activities.add(tika2);
        activities.add(tika3);
        activities.add(deusi1);
        activities.add(deusi2);
        activities.add(deusi3);

        // Master planner
        System.out.println("^_^ Welcome to the Dashain Festival Master Planner ^_^");
        executeFestivalPlan(activities);
        System.out.println(" Dashain chaos successfully managed by our planner!!");
    }
}
