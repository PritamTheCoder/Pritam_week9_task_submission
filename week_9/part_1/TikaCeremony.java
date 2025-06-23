// Tika Ceremony Planning Module
package part_1;

// base class exception
class InvalidGuestCountException extends FestivalPlanningException{
    public InvalidGuestCountException(String message){
        super(message);
    }
}

class BudgetExceedException extends FestivalPlanningException {
    public BudgetExceedException(String message){
        super(message);
    }
}

public class TikaCeremony extends Activity{
    public int expectedGuests;
    public String mainFamilyElder;
    public TikaCeremony(double estimatedCost, int expectedGuests, String mainFamilyElder){
        super("Tika Ceremony", estimatedCost);
        this.expectedGuests = expectedGuests;
        this.mainFamilyElder = mainFamilyElder;
    }

    @Override
    public void planActivity() throws FestivalPlanningException{
        if(expectedGuests < 5){
            throw new InvalidGuestCountException("Not enough guests for a lively Tika! Is everyone on vacation?");
        }else if(estimatedCost > 50000){
        throw new BudgetExceedException("Tika budget too high! Is this for the whole village?");
    }else {
        System.out.println("Tika ceremony with " + mainFamilyElder + " planned successfully for " + expectedGuests + " guests!");
    }
}

public static void main(String[] args) {
    TikaCeremony[] tikas = {
        new TikaCeremony(30000, 6, "Grandfather"),
        new TikaCeremony(60000, 10, "Father"),
        new TikaCeremony(25000, 3, "Uncle"),
        new TikaCeremony(40000, 6, "Aunt")
};
    for (TikaCeremony tika : tikas ){
        tika.displayOverview();
        try {
            tika.planActivity();
        }catch (InvalidGuestCountException e) {
                System.out.println("InvalidGuestCountException: " + e.getMessage());
            } catch (BudgetExceedException e) {
                System.out.println("BudgetExceededException: " + e.getMessage());
            } catch (FestivalPlanningException e) {
                System.out.println("FestivalPlanningException: " + e.getMessage());
            }
            System.out.println("---------------------------------");
        }
    }
}