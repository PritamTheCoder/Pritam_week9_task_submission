package part_1;
// Abstract Festival Activity Planner

class FestivalPlanningException extends Exception{
   public FestivalPlanningException(String message){
    super(message);
}
}

abstract class Activity{
    protected String activityName;
    protected double estimatedCost;
    public Activity(String activityName, double estimatedCost){
        this.activityName = activityName;
        this.estimatedCost = estimatedCost;
    }

   public abstract void planActivity() throws FestivalPlanningException;

  public void displayOverview(){
    System.out.println("Activity Name : " + activityName);
    System.out.println("Estimation Cost : $"+ estimatedCost);
   }
}

class DashainActivity extends Activity{
    public DashainActivity(String activityName, double estimatedCost){
        super(activityName, estimatedCost);
    }

    @Override
    public void planActivity() throws FestivalPlanningException {
            if(estimatedCost < 500){
                System.out.println("Not enough budget. Please increase the budget.");
            }
        else{
            System.out.println("Event planned successfully.");
         }
    }
}

public class FestivalActivity{
    public static void main(String[] args){
        DashainActivity dashainActivity = new DashainActivity("Tika",5000);
        DashainActivity kite = new DashainActivity("Kite",200);
        DashainActivity travel = new DashainActivity("Travel",1200);

        dashainActivity.displayOverview();
        try{
            dashainActivity.planActivity();
        }catch (FestivalPlanningException e){
            System.out.println("Error!" + e.getMessage());
        }
        kite.displayOverview();
        try{
            kite.planActivity();
        }catch (FestivalPlanningException e){
            System.out.println("Error!" + e.getMessage());
        }

        travel.displayOverview();
        try{
            travel.planActivity();
        }catch (FestivalPlanningException e){
            System.out.println("Error!" + e.getMessage());
        }
    }
}
