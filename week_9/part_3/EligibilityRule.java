
// Custom checked exception for enrollment denial
class EnrollmentDeniedException extends Exception{
    public EnrollmentDeniedException(String message){
        super(message);
    }
}

// Functional interface for course enrollment eligibility rule
@FunctionalInterface
public interface EligibilityRule {

    boolean isEligible(String studentId, String courseId) throws EnrollmentDeniedException;
}