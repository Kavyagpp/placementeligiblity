/**
 * EligibilityRule.java
 *
 * Demonstrates the Core OOP Concepts: ABSTRACTION and INTERFACES
 *
 * Why an Interface is used here:
 * 1. Abstraction:
 *    - An interface defines "WHAT" must be done, without dictating "HOW" it is done.
 *    - It provides a standardized contract that all placement eligibility rules must follow.
 *    - Any class that implements EligibilityRule agrees to provide concrete logic for
 *      evaluating whether a candidate satisfies a specific job criterion.
 *
 * 2. Separation of Concerns:
 *    - The decision engine doesn't need to know the internal math or checks of individual rules.
 *    - It only interacts with this common interface contract.
 *
 * 3. Extensibility (Open-Closed Principle):
 *    - New rules (e.g., DomainSkillRule, AgeRule, GapYearRule) can be added seamlessly
 *      by implementing this interface, without modifying existing evaluator logic.
 */
public interface EligibilityRule {

    /**
     * Evaluates if a given candidate satisfies the rule for the specified job.
     *
     * @param candidate The candidate being evaluated
     * @param job       The job whose requirements are being checked
     * @return true if the rule is satisfied; false otherwise
     */
    boolean isSatisfied(Candidate candidate, Job job);

    /**
     * Provides the human-readable failure reason if the rule is not satisfied.
     *
     * @return the failure explanation string
     */
    String getFailureReason();
}
