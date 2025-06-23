//Student Dashboard Enrollment Status

public class StudentDashboard {
    public void displayCourseStatus(String studentId, String courseId, EligibilityRule rule){
        System.out.println("Checking enrollment status for " + studentId + " in " + courseId + "...");

        try {
            if(rule.isEligible(studentId, courseId)){
                System.out.println("You are enrolled! Access course materials now. ^-^");
            }
        } catch (EnrollmentDeniedException e) {
            System.out.println(" Enrollment denied: " + e.getMessage() + ". Please contact support.");
        }finally{
            System.out.println("Status check completed for " + studentId + ".");
            System.out.println("--------------------------------------------------");
        }
    }
    // main method for testing
    public static void main(String[] args) {
        StudentDashboard dashboard = new StudentDashboard();

        // Lambda expression implementing EligibilityRule
        EligibilityRule rule = (studentId, courseId) -> {
            if(studentId.equals("SKILL999")){
                throw new EnrollmentDeniedException("Student account suspended due to outstanding fees, Roshan!");
            } if (courseId.equals("JAVA101")) {
                if (!studentId.startsWith("SKILL")) {
                    throw new EnrollmentDeniedException("Invalid student ID format. Please use 'SKILL' prefix, Anisha!");
                }
                return true;
            }
            return false;
        };

         // Test Case 1: Valid enrollment
        dashboard.displayCourseStatus("SKILL123", "JAVA101", rule);

        // Test Case 2: Suspended student
        dashboard.displayCourseStatus("SKILL999", "PYTHON202", rule);

        // Test Case 3: Invalid student ID format
        dashboard.displayCourseStatus("STUDENT001", "JAVA101", rule);
    }
}
