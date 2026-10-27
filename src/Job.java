/**
 * Job.java
 *
 * Represents an employment opportunity with its specific placement eligibility criteria.
 *
 * Demonstrates:
 * - Encapsulation: Storing job criteria in private fields with controlled getter access.
 * - Flexibility: Different Job instances can have completely different requirements
 *   without changing the underlying evaluation code.
 */
public class Job {

    // Private fields: Keep job requirements encapsulated
    private String title;
    private double minPercentage;
    private int maxBacklogs;
    private int minGraduationYear;
    private int maxGraduationYear;

    /**
     * Parameterized Constructor to create a Job with its required criteria.
     *
     * @param title             Title or role of the job (e.g., "Software Developer")
     * @param minPercentage     Minimum academic percentage required
     * @param maxBacklogs       Maximum active backlogs allowed
     * @param minGraduationYear Earliest acceptable graduation year (inclusive)
     * @param maxGraduationYear Latest acceptable graduation year (inclusive)
     */
    public Job(String title, double minPercentage, int maxBacklogs, int minGraduationYear, int maxGraduationYear) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Job title cannot be empty.");
        }
        if (minPercentage < 0.0 || minPercentage > 100.0) {
            throw new IllegalArgumentException("Minimum percentage must be between 0.0 and 100.0.");
        }
        if (maxBacklogs < 0) {
            throw new IllegalArgumentException("Maximum backlogs cannot be negative.");
        }
        if (minGraduationYear > maxGraduationYear) {
            throw new IllegalArgumentException("Minimum graduation year cannot exceed maximum graduation year.");
        }

        this.title = title.trim();
        this.minPercentage = minPercentage;
        this.maxBacklogs = maxBacklogs;
        this.minGraduationYear = minGraduationYear;
        this.maxGraduationYear = maxGraduationYear;
    }

    // Public Getters

    /**
     * @return the job title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the minimum required percentage
     */
    public double getMinPercentage() {
        return minPercentage;
    }

    /**
     * @return the maximum allowed active backlogs
     */
    public int getMaxBacklogs() {
        return maxBacklogs;
    }

    /**
     * @return the minimum graduation year accepted
     */
    public int getMinGraduationYear() {
        return minGraduationYear;
    }

    /**
     * @return the maximum graduation year accepted
     */
    public int getMaxGraduationYear() {
        return maxGraduationYear;
    }

    @Override
    public String toString() {
        return String.format("%s (Min %%: %.1f%%, Max Backlogs: %d, Batch: %d-%d)",
                title, minPercentage, maxBacklogs, minGraduationYear, maxGraduationYear);
    }
}
