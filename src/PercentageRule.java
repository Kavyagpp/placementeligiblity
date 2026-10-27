/**
 * PercentageRule.java
 *
 * Implements the EligibilityRule interface to verify whether a candidate's
 * academic score meets the minimum percentage threshold required by a job.
 *
 * Demonstrates:
 * - Interface Implementation: using the 'implements' keyword.
 * - Method Overriding: providing concrete behavior for interface methods using '@Override'.
 */
public class PercentageRule implements EligibilityRule {

    /**
     * Checks if the candidate's percentage is greater than or equal to the job's minimum percentage.
     *
     * @param candidate Candidate to evaluate
     * @param job       Job requirements
     * @return true if percentage requirement is met, false otherwise
     */
    @Override
    public boolean isSatisfied(Candidate candidate, Job job) {
        return candidate.getPercentage() >= job.getMinPercentage();
    }

    /**
     * Returns the exact failure message when percentage is below requirement.
     *
     * @return failure reason
     */
    @Override
    public String getFailureReason() {
        return "Percentage is below the minimum requirement.";
    }
}
