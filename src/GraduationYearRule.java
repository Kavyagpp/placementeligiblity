/**
 * GraduationYearRule.java
 *
 * Implements the EligibilityRule interface to verify whether a candidate's
 * graduation year falls within the job's accepted batch range (inclusive of endpoints).
 *
 * Demonstrates:
 * - Interface Implementation: using the 'implements' keyword.
 * - Method Overriding: using '@Override' to fulfill the interface contract.
 */
public class GraduationYearRule implements EligibilityRule {

    /**
     * Checks if the candidate's graduation year is between minGraduationYear and maxGraduationYear (inclusive).
     *
     * @param candidate Candidate to evaluate
     * @param job       Job requirements
     * @return true if graduation year is in range, false otherwise
     */
    @Override
    public boolean isSatisfied(Candidate candidate, Job job) {
        int gradYear = candidate.getGraduationYear();
        return gradYear >= job.getMinGraduationYear() && gradYear <= job.getMaxGraduationYear();
    }

    /**
     * Returns the exact failure message when graduation year is outside range.
     *
     * @return failure reason
     */
    @Override
    public String getFailureReason() {
        return "Graduation year is outside the permitted range.";
    }
}
