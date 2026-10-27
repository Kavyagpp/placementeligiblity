/**
 * BacklogRule.java
 *
 * Implements the EligibilityRule interface to verify whether a candidate's
 * active backlog count does not exceed the job's maximum allowed limit.
 *
 * Demonstrates:
 * - Interface Implementation: using the 'implements' keyword.
 * - Method Overriding: using '@Override' to fulfill the interface contract.
 */
public class BacklogRule implements EligibilityRule {

    /**
     * Checks if the candidate's active backlogs are within the job's permitted limit.
     *
     * @param candidate Candidate to evaluate
     * @param job       Job requirements
     * @return true if backlog count is within allowable limit, false otherwise
     */
    @Override
    public boolean isSatisfied(Candidate candidate, Job job) {
        return candidate.getBacklogs() <= job.getMaxBacklogs();
    }

    /**
     * Returns the exact failure message when candidate has excessive backlogs.
     *
     * @return failure reason
     */
    @Override
    public String getFailureReason() {
        return "Candidate has more active backlogs than permitted.";
    }
}
