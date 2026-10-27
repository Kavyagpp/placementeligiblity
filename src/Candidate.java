/**
 * Candidate.java
 *
 * Represents a student/candidate registering for campus placements.
 *
 * Demonstrates the Core OOP Concept: ENCAPSULATION
 * - Encapsulation is the bundling of data (fields) and methods that operate on that data
 *   into a single unit (class), while restricting direct access to internal state.
 *
 * Key Concepts Explained:
 * 1. private Access Modifier:
 *    - Declaring fields as 'private' hides them from direct access outside this class.
 *    - This protects the internal state from unauthorized or unintended modifications.
 *
 * 2. Constructor:
 *    - A special method invoked during object instantiation (using 'new') to initialize state.
 *    - It guarantees that a Candidate object is always created in a valid and complete state.
 *
 * 3. 'this' Keyword:
 *    - 'this' refers to the current instance of the class.
 *    - It resolves shadowing between constructor parameters and instance fields (e.g., this.name = name).
 *
 * 4. Public Getters:
 *    - Public getter methods provide controlled, read-only access to the private fields.
 *    - No setters are provided here, making candidate data immutable after creation.
 */
public class Candidate {

    // Private fields: Accessible ONLY within this Candidate class (Data Hiding)
    private String name;
    private double percentage;
    private int backlogs;
    private int graduationYear;

    /**
     * Parameterized Constructor to initialize candidate details.
     * Ensures candidate objects are created with validated data.
     *
     * @param name           Full name of the candidate (cannot be null or empty)
     * @param percentage     Academic percentage (0.0 to 100.0)
     * @param backlogs       Number of active backlogs (cannot be negative)
     * @param graduationYear Year of graduation (reasonable year range)
     */
    public Candidate(String name, double percentage, int backlogs, int graduationYear) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Candidate name cannot be empty.");
        }
        if (percentage < 0.0 || percentage > 100.0) {
            throw new IllegalArgumentException("Percentage must be between 0.0 and 100.0.");
        }
        if (backlogs < 0) {
            throw new IllegalArgumentException("Active backlogs cannot be negative.");
        }
        if (graduationYear < 1900 || graduationYear > 2100) {
            throw new IllegalArgumentException("Graduation year must be between 1900 and 2100.");
        }

        // 'this' refers to the current object's fields to distinguish them from the parameter variables
        this.name = name.trim();
        this.percentage = percentage;
        this.backlogs = backlogs;
        this.graduationYear = graduationYear;
    }

    // Public Getters: Expose data safely in a read-only manner

    /**
     * @return the candidate's full name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the candidate's academic percentage
     */
    public double getPercentage() {
        return percentage;
    }

    /**
     * @return the number of active backlogs
     */
    public int getBacklogs() {
        return backlogs;
    }

    /**
     * @return the graduation year
     */
    public int getGraduationYear() {
        return graduationYear;
    }

    @Override
    public String toString() {
        return String.format("Candidate[Name='%s', Percentage=%.2f%%, Backlogs=%d, GraduationYear=%d]",
                name, percentage, backlogs, graduationYear);
    }
}
