import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Main.java
 *
 * Console-based Driver Application for the Placement Eligibility Decision Engine.
 * KodNest Placement Readiness Program - Module 6: Abstraction and Interfaces.
 *
 * Demonstrates:
 * - Menu-driven console UI with robust input validation.
 * - Separation of concerns across modular helper methods.
 * - Encapsulation (Candidate and Job state management).
 * - Abstraction & Interfaces (EligibilityRule interface contract).
 * - Polymorphism (EligibilityEvaluator applying rules dynamically).
 * - Collections (ArrayList storing candidates, jobs, rules, and failure reasons).
 */
public class Main {

    // In-memory collections (resets when application terminates)
    private static final List<Candidate> candidates = new ArrayList<>();
    private static final List<Job> jobs = new ArrayList<>();
    private static final EligibilityEvaluator evaluator = new EligibilityEvaluator();

    public static void main(String[] args) {
        // Preload standard sample placement job profiles
        initializeSampleJobs();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        printHeader();

        while (running) {
            displayMenu();
            int choice = readInt(scanner, "Enter your choice (1-5): ");
            System.out.println();

            switch (choice) {
                case 1:
                    registerCandidate(scanner);
                    break;
                case 2:
                    viewCandidates();
                    break;
                case 3:
                    viewJobs();
                    break;
                case 4:
                    evaluateCandidate(scanner);
                    break;
                case 5:
                    exitApplication();
                    running = false;
                    break;
                default:
                    System.out.println("[!] Invalid choice. Please enter a number between 1 and 5.\n");
            }
        }

        scanner.close();
    }

    /**
     * Preloads configurable sample jobs as specified in requirements.
     * Disclaimer: Actual placement criteria depend strictly on individual hiring companies.
     */
    private static void initializeSampleJobs() {
        jobs.add(new Job("Software Developer", 60.0, 0, 2025, 2026));
        jobs.add(new Job("Graduate Trainee", 50.0, 1, 2024, 2026));
    }

    /**
     * Displays application welcome header.
     */
    private static void printHeader() {
        System.out.println("================================================================");
        System.out.println("           PLACEMENT ELIGIBILITY DECISION ENGINE                ");
        System.out.println("    KodNest Placement Readiness | Module 6: Core Java OOP       ");
        System.out.println("================================================================");
        System.out.println("Notice: Placement criteria shown are sample rules for training.");
        System.out.println("In-memory storage is active: data resets when program exits.");
        System.out.println("================================================================\n");
    }

    /**
     * Displays main menu options.
     */
    private static void displayMenu() {
        System.out.println("------------------------- MAIN MENU ----------------------------");
        System.out.println("1. Register Candidate");
        System.out.println("2. View Candidates");
        System.out.println("3. View Available Jobs");
        System.out.println("4. Evaluate Candidate");
        System.out.println("5. Exit");
        System.out.println("----------------------------------------------------------------");
    }

    /**
     * Option 1: Registers a new candidate with comprehensive input validation.
     *
     * @param scanner Active Scanner instance
     */
    private static void registerCandidate(Scanner scanner) {
        System.out.println(">>> REGISTER NEW CANDIDATE <<<");

        // 1. Candidate Full Name Validation (including spaces, cannot be empty)
        String name = readNonEmptyString(scanner, "Enter Candidate Full Name: ");

        // 2. Percentage Validation (0.0 to 100.0)
        double percentage;
        while (true) {
            percentage = readDouble(scanner, "Enter Academic Percentage (0 - 100): ");
            if (percentage >= 0.0 && percentage <= 100.0) {
                break;
            }
            System.out.println("[!] Error: Percentage must be between 0.0 and 100.0. Please re-enter.");
        }

        // 3. Backlog Count Validation (non-negative)
        int backlogs;
        while (true) {
            backlogs = readInt(scanner, "Enter Number of Active Backlogs: ");
            if (backlogs >= 0) {
                break;
            }
            System.out.println("[!] Error: Active backlogs cannot be negative. Please re-enter.");
        }

        // 4. Graduation Year Validation (reasonable range: 1900 - 2100)
        int graduationYear;
        while (true) {
            graduationYear = readInt(scanner, "Enter Graduation Year (1900 - 2100): ");
            if (graduationYear >= 1900 && graduationYear <= 2100) {
                break;
            }
            System.out.println("[!] Error: Graduation year must be between 1900 and 2100. Please re-enter.");
        }

        // Create encapsulated Candidate object and store in collection
        Candidate candidate = new Candidate(name, percentage, backlogs, graduationYear);
        candidates.add(candidate);

        System.out.println("\n[OK] Candidate registered successfully! (Candidate ID: " + candidates.size() + ")\n");
    }

    /**
     * Option 2: Displays all registered candidates in formatted tabular structure.
     */
    private static void viewCandidates() {
        System.out.println(">>> REGISTERED CANDIDATES <<<");
        if (candidates.isEmpty()) {
            System.out.println("[i] No candidates registered yet. Please register a candidate first.\n");
            return;
        }

        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-25s | %-12s | %-10s | %-15s%n",
                "ID", "Candidate Name", "Percentage", "Backlogs", "Graduation Year");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < candidates.size(); i++) {
            Candidate c = candidates.get(i);
            System.out.printf("%-5d | %-25s | %10.2f%% | %-10d | %-15d%n",
                    (i + 1), c.getName(), c.getPercentage(), c.getBacklogs(), c.getGraduationYear());
        }
        System.out.println("----------------------------------------------------------------------------------\n");
    }

    /**
     * Option 3: Displays all available job opportunities and their criteria.
     */
    private static void viewJobs() {
        System.out.println(">>> AVAILABLE JOBS & ELIGIBILITY CRITERIA <<<");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-22s | %-12s | %-12s | %-15s%n",
                "ID", "Job Title", "Min Percentage", "Max Backlogs", "Accepted Batches");
        System.out.println("----------------------------------------------------------------------------------");
        for (int i = 0; i < jobs.size(); i++) {
            Job j = jobs.get(i);
            System.out.printf("%-5d | %-22s | %12.1f%% | %-12d | %d - %d%n",
                    (i + 1), j.getTitle(), j.getMinPercentage(), j.getMaxBacklogs(),
                    j.getMinGraduationYear(), j.getMaxGraduationYear());
        }
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("Note: Criteria can vary across actual campus placement drives.\n");
    }

    /**
     * Option 4: Evaluates candidate against a selected job role.
     *
     * @param scanner Active Scanner instance
     */
    private static void evaluateCandidate(Scanner scanner) {
        System.out.println(">>> EVALUATE CANDIDATE ELIGIBILITY <<<");

        if (candidates.isEmpty()) {
            System.out.println("[!] No candidates available to evaluate. Please register a candidate first.\n");
            return;
        }

        // Step 1: Select Candidate
        viewCandidates();
        int candidateChoice;
        while (true) {
            candidateChoice = readInt(scanner, "Select Candidate ID (1 to " + candidates.size() + "): ");
            if (candidateChoice >= 1 && candidateChoice <= candidates.size()) {
                break;
            }
            System.out.println("[!] Error: Invalid Candidate ID. Please select from the list.");
        }
        Candidate selectedCandidate = candidates.get(candidateChoice - 1);

        // Step 2: Select Job
        System.out.println();
        viewJobs();
        int jobChoice;
        while (true) {
            jobChoice = readInt(scanner, "Select Job ID (1 to " + jobs.size() + "): ");
            if (jobChoice >= 1 && jobChoice <= jobs.size()) {
                break;
            }
            System.out.println("[!] Error: Invalid Job ID. Please select from the list.");
        }
        Job selectedJob = jobs.get(jobChoice - 1);

        // Step 3: Run Evaluation via Polymorphic Rule Pipeline
        List<String> failureReasons = evaluator.evaluate(selectedCandidate, selectedJob);

        // Step 4: Display Results
        System.out.println();
        System.out.println("======================= EVALUATION RESULT =======================");
        System.out.println("Candidate Name : " + selectedCandidate.getName());
        System.out.println("Applied Job    : " + selectedJob.getTitle());
        System.out.println("-----------------------------------------------------------------");

        if (failureReasons.isEmpty()) {
            System.out.println("Decision       : ELIGIBLE");
            System.out.println("Status Message : Congratulations! Candidate satisfies all eligibility criteria.");
        } else {
            System.out.println("Decision       : NOT ELIGIBLE");
            System.out.println("Failure Reasons (" + failureReasons.size() + " issue(s) detected):");
            for (String reason : failureReasons) {
                System.out.println("  * " + reason);
            }
        }
        System.out.println("=================================================================\n");
    }

    /**
     * Option 5: Exits the application gracefully.
     */
    private static void exitApplication() {
        System.out.println("================================================================");
        System.out.println("Thank you for using the Placement Eligibility Decision Engine!");
        System.out.println("Wishing you all the very best in your KodNest placements.");
        System.out.println("================================================================");
    }

    // =========================================================================
    // ROBUST INPUT UTILITY METHODS (Prevents Scanner crashes on non-numeric input)
    // =========================================================================

    /**
     * Safely reads an integer from the console, reprompting if invalid input is provided.
     *
     * @param scanner Active Scanner
     * @param prompt  User prompt message
     * @return Validated integer
     */
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a valid integer number.");
            }
        }
    }

    /**
     * Safely reads a double from the console, reprompting if invalid input is provided.
     *
     * @param scanner Active Scanner
     * @param prompt  User prompt message
     * @return Validated double
     */
    private static double readDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("[!] Invalid input! Please enter a valid decimal number (e.g., 75.5).");
            }
        }
    }

    /**
     * Safely reads a non-empty string from the console.
     *
     * @param scanner Active Scanner
     * @param prompt  User prompt message
     * @return Validated non-empty string
     */
    private static String readNonEmptyString(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("[!] Error: Input cannot be empty! Please enter a valid value.");
        }
    }
}
