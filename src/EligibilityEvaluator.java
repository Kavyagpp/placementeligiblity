import java.util.ArrayList;
import java.util.List;

/**
 * EligibilityEvaluator.java
 *
 * Coordinates the evaluation of a candidate against a job by applying all configured rules.
 *
 * Demonstrates the Core OOP Concepts:
 * 1. POLYMORPHISM:
 *    - The rules list contains objects of different concrete classes (PercentageRule,
 *      BacklogRule, GraduationYearRule), all treated uniformly through the common
 *      interface type 'EligibilityRule'.
 *    - In the loop:
 *          for (EligibilityRule rule : rules) {
 *              rule.isSatisfied(candidate, job);
 *          }
 *      Dynamic Method Dispatch executes the specific class's version of isSatisfied() at runtime.
 *
 * 2. COLLECTIONS (List & ArrayList):
 *    - List<EligibilityRule> holds the rules flexibly.
 *    - List<String> collects and returns all failure reasons.
 */
public class EligibilityEvaluator {

    // List of eligibility rules stored using the interface type (Polymorphism)
    private List<EligibilityRule> rules;

    /**
     * Default constructor: initializes the evaluator with standard placement rules.
     */
    public EligibilityEvaluator() {
        this.rules = new ArrayList<>();
        this.rules.add(new PercentageRule());
        this.rules.add(new BacklogRule());
        this.rules.add(new GraduationYearRule());
    }

    /**
     * Overloaded constructor allowing custom rule sets to be injected.
     *
     * @param customRules List of rules to apply
     */
    public EligibilityEvaluator(List<EligibilityRule> customRules) {
        this.rules = new ArrayList<>(customRules);
    }

    /**
     * Adds an additional rule to the evaluation pipeline.
     *
     * @param rule The rule to add
     */
    public void addRule(EligibilityRule rule) {
        if (rule != null) {
            this.rules.add(rule);
        }
    }

    /**
     * Evaluates a candidate against all rules for a given job.
     * Evaluates EVERY rule without stopping at the first failure.
     *
     * @param candidate The candidate to evaluate
     * @param job       The job criteria
     * @return A list of failure reason messages. If empty, the candidate is eligible!
     */
    public List<String> evaluate(Candidate candidate, Job job) {
        List<String> failureReasons = new ArrayList<>();

        if (candidate == null || job == null) {
            throw new IllegalArgumentException("Candidate and Job cannot be null.");
        }

        // Polymorphic iteration: calls the correct subclass method at runtime
        for (EligibilityRule rule : rules) {
            if (!rule.isSatisfied(candidate, job)) {
                // Rule failed: record the specific reason
                failureReasons.add(rule.getFailureReason());
            }
        }

        return failureReasons;
    }

    /**
     * Helper method to quickly determine eligibility.
     *
     * @param candidate Candidate to evaluate
     * @param job       Job requirements
     * @return true if candidate meets all criteria, false otherwise
     */
    public boolean isEligible(Candidate candidate, Job job) {
        return evaluate(candidate, job).isEmpty();
    }
}
