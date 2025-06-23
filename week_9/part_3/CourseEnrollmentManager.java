
public class CourseEnrollmentManager {
    public static void enrollStudent(String studentId, String courseId, EligibilityRule rule){
        System.out.println("Attempting to enroll " + studentId + " in " + courseId + "...");

        try {
            if(rule.isEligible(studentId, courseId)){
                System.out.println("Enrollment successful for " + studentId + " in " + courseId + "! Happy learning!");
            }else{
                System.out.println("Enrollment failed for " + studentId + ": Not eligible based on course rules.");
            }
        } catch (EnrollmentDeniedException e) {
            System.out.println("Enrollment failed for " + studentId + ": " + e.getMessage());
        }
         System.out.println("----------------------------------------------------");
    }
   public static void main(String[] args) {
    // Lambda expression implementing EligibilityRule
    EligibilityRule rule = (studentId, courseId) -> {
        if(studentId.equals("SKILL999")){
            throw new EnrollmentDeniedException("Student account suspended due to outstanding fees, Roshan!");
        }if (courseId.equals("JAVA101")) {
            if (!studentId.startsWith("SKILL")) {
            throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Anisha!");
    }
    return true;
   }
   return false;
};
    // Test cases
    // Valid case
    enrollStudent("SKILL123", "JAVA101", rule);

    // Suspended students
    enrollStudent("SKILL999", "JAVA101", rule);

    // Invalid student id format
    enrollStudent("USER101", "JAVA101", rule);

    // General not eligible (valid ID but different course)
    enrollStudent("SKILL444", "PYTHON250", rule);

    // Another ineligible case
    enrollStudent("HELLO123", "DSA102", rule);
}
}