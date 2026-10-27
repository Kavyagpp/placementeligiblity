# Placement Eligibility Decision Engine

A production-grade, beginner-friendly Core Java console application built as part of the **KodNest Placement Readiness Program** (**Module 6: Abstraction and Interfaces**, **Topic 1: Build the Core Project with Encapsulation**).

---

## 1. Project Overview

The **Placement Eligibility Decision Engine** automates the evaluation of student candidates against technical and non-technical hiring criteria set by campus recruiters. In campus placements, each recruiting company defines distinct academic eligibility benchmarks (such as cutoff percentage, maximum allowed backlogs, and eligible graduation batches).

This application allows training and placement coordinators to:
1. Register candidate profiles with academic and batch details.
2. Store configurable job criteria without hardcoded business rules.
3. Automatically evaluate candidates across multiple independent rules.
4. Report whether a candidate is **ELIGIBLE** or **NOT ELIGIBLE**, presenting every applicable failure reason rather than stopping at the first failure.

---

## 2. Objectives

- Implement a real-world placement domain project using **Core Java** principles.
- Apply **Encapsulation** to protect candidate and job state.
- Implement **Abstraction** and **Interfaces** to decouple eligibility rule definitions from their concrete implementations.
- Leverage **Runtime Polymorphism (Dynamic Method Dispatch)** to evaluate rules uniformly through a common interface reference.
- Use Java **Collections Framework** (`ArrayList`, `List`) to handle dynamic in-memory data.
- Build a menu-driven, resilient console interface that gracefully handles invalid inputs without crashing.

---

## 3. Features

* **Candidate Registration with Validation:**
  * Captures full candidate name (including spaces), academic percentage, active backlog count, and graduation year.
  * Validates data bounds: non-empty name, percentage between `0.0` and `100.0`, non-negative backlogs, and realistic graduation year range (`1900 - 2100`).
* **Configurable Job Profiles:**
  * Represents job openings with distinct criteria for minimum percentage, maximum backlogs, and graduation year ranges.
  * Completely configurable; avoids hardcoded eligibility conditions throughout the code.
* **Pluggable Rule Pipeline:**
  * Rules implement a shared `EligibilityRule` interface contract.
  * Evaluator applies all rules sequentially and aggregates every failure message.
* **Interactive Console Menu:**
  * Simple 5-option interactive menu.
  * Safe numeric parsers that prevent `InputMismatchException` crashes when non-numeric input is supplied.
* **Clear Decision Output:**
  * Clearly reports `ELIGIBLE` or `NOT ELIGIBLE` with itemized failure reasons.

---

## 4. Technologies Used

* **Language:** Java (JDK 8 or higher; tested on JDK 26)
* **Application Type:** Console / CLI Application
* **External Libraries:** None (Pure Standard Java SE Library)
* **Build Tool:** Plain `javac` compiler
* **Version Control:** Git & GitHub

---

## 5. Project Structure

```
placement-eligibility-decision-engine/
│
├── .gitignore
├── README.md
└── src/
    ├── Main.java                  # Console application driver & interactive menu
    ├── Candidate.java             # Encapsulated candidate model with validation
    ├── Job.java                   # Encapsulated job profile model
    ├── EligibilityRule.java       # Interface defining the rule abstraction contract
    ├── PercentageRule.java        # Concrete rule for academic percentage check
    ├── BacklogRule.java           # Concrete rule for active backlog count check
    ├── GraduationYearRule.java    # Concrete rule for graduation batch range check
    └── EligibilityEvaluator.java  # Polymorphic engine applying all rules
```

> **Note:** All Java source files reside directly in `src/` without package declarations to permit clean compilation and execution using standard `javac` and `java` commands.

---

## 6. Object-Oriented Programming (OOP) Concepts Demonstrated

### 1. Encapsulation (`Candidate.java`, `Job.java`)
* **Data Hiding:** Fields such as `name`, `percentage`, `backlogs`, and `graduationYear` are declared `private`. They cannot be directly accessed or modified by outside classes.
* **Constructors:** Parameterized constructors ensure objects are initialized only in a complete, valid state.
* **The `this` Keyword:** Used inside constructors to resolve variable shadowing between incoming parameters and instance variables (e.g., `this.name = name`).
* **Controlled Access:** Public getter methods (`getName()`, `getPercentage()`, etc.) provide read-only access to internal state. No arbitrary mutator methods (setters) are exposed.

### 2. Abstraction (`EligibilityRule.java`)
* **Contract over Implementation:** Abstraction hides complex logic and exposes only essential operations. The `EligibilityRule` interface defines *what* an eligibility check must do:
  ```java
  public interface EligibilityRule {
      boolean isSatisfied(Candidate candidate, Job job);
      String getFailureReason();
  }
  ```
* High-level components (such as `EligibilityEvaluator`) depend upon this abstraction, not on low-level implementation details.

### 3. Interfaces (`PercentageRule.java`, `BacklogRule.java`, `GraduationYearRule.java`)
* An interface establishes a formal contract.
* Concrete classes use the `implements` keyword to guarantee they provide concrete behavior for `isSatisfied` and `getFailureReason`.
* The `@Override` annotation is used to enforce compile-time verification of method signatures.

### 4. Polymorphism (`EligibilityEvaluator.java`)
* **Interface-based Polymorphism:** The evaluator stores rules using a collection of interface references:
  ```java
  List<EligibilityRule> rules = new ArrayList<>();
  ```
* **Dynamic Method Dispatch:** When iterating through the rules:
  ```java
  for (EligibilityRule rule : rules) {
      if (!rule.isSatisfied(candidate, job)) {
          failureReasons.add(rule.getFailureReason());
      }
  }
  ```
  Java determines which class's `isSatisfied()` and `getFailureReason()` method to execute at runtime based on the actual object (`PercentageRule`, `BacklogRule`, or `GraduationYearRule`).

### 5. Java Collections Framework
* `List<Candidate>` and `List<Job>` dynamically manage in-memory records.
* `List<String>` collects and returns variable numbers of failure reasons.

---

## 7. Sample Eligibility Criteria

The application comes preloaded with two sample job roles:

| Job ID | Job Title | Minimum Percentage | Max Permitted Backlogs | Accepted Graduation Batches |
|---|---|---|---|---|
| **1** | **Software Developer** | 60.0% | 0 | 2025 – 2026 |
| **2** | **Graduate Trainee** | 50.0% | 1 | 2024 – 2026 |

> **Disclaimer:** These criteria are sample configurations used for instructional and testing purposes. In actual campus placements, eligibility thresholds are established independently by each recruiting organization.

---

## 8. How to Compile and Run

Open your terminal or command prompt in the project root directory (`placement-eligibility-decision-engine`):

### Step 1: Compile all Java source files
```bash
javac src/*.java
```

### Step 2: Run the application
```bash
java -cp src Main
```

---

## 9. Sample Execution Walkthrough

### Scenario A: Registering a Candidate
```text
------------------------- MAIN MENU ----------------------------
1. Register Candidate
2. View Candidates
3. View Available Jobs
4. Evaluate Candidate
5. Exit
----------------------------------------------------------------
Enter your choice (1-5): 1

>>> REGISTER NEW CANDIDATE <<<
Enter Candidate Full Name: Aarav Sharma
Enter Academic Percentage (0 - 100): 78.5
Enter Number of Active Backlogs: 0
Enter Graduation Year (1900 - 2100): 2025

[OK] Candidate registered successfully! (Candidate ID: 1)
```

### Scenario B: Evaluating an Eligible Candidate
```text
Enter your choice (1-5): 4

>>> EVALUATE CANDIDATE ELIGIBILITY <<<
>>> REGISTERED CANDIDATES <<<
----------------------------------------------------------------------------------
ID    | Candidate Name            | Percentage   | Backlogs   | Graduation Year
----------------------------------------------------------------------------------
1     | Aarav Sharma              |      78.50% | 0          | 2025           
----------------------------------------------------------------------------------

Select Candidate ID (1 to 1): 1

>>> AVAILABLE JOBS & ELIGIBILITY CRITERIA <<<
----------------------------------------------------------------------------------
ID    | Job Title              | Min Percentage | Max Backlogs | Accepted Batches
----------------------------------------------------------------------------------
1     | Software Developer     |         60.0% | 0            | 2025 - 2026
2     | Graduate Trainee       |         50.0% | 1            | 2024 - 2026
----------------------------------------------------------------------------------
Note: Criteria can vary across actual campus placement drives.

Select Job ID (1 to 2): 1

======================= EVALUATION RESULT =======================
Candidate Name : Aarav Sharma
Applied Job    : Software Developer
-----------------------------------------------------------------
Decision       : ELIGIBLE
Status Message : Congratulations! Candidate satisfies all eligibility criteria.
=================================================================
```

### Scenario C: Evaluating an Ineligible Candidate (Multiple Failures)
```text
======================= EVALUATION RESULT =======================
Candidate Name : Vikram Singh
Applied Job    : Software Developer
-----------------------------------------------------------------
Decision       : NOT ELIGIBLE
Failure Reasons (3 issue(s) detected):
  * Percentage is below the minimum requirement.
  * Candidate has more active backlogs than permitted.
  * Graduation year is outside the permitted range.
=================================================================
```

### Scenario D: Resilient Input Handling (Letters Instead of Numbers)
```text
Enter Academic Percentage (0 - 100): abc
[!] Invalid input! Please enter a valid decimal number (e.g., 75.5).
Enter Academic Percentage (0 - 100): 72.5
```

---

## 10. Current Limitations

1. **In-Memory Storage:** Registered candidate records are stored in JVM memory (`ArrayList`). All records reset when the application terminates.
2. **Console Interface:** Interaction is text-based via standard command-line I/O.
3. **No Authentication:** All users share administrative access to register and evaluate candidates.

---

## 11. Possible Future Improvements

1. **File I/O or Database Persistence:** Save candidates and job roles to CSV, JSON, or an RDBMS (e.g., MySQL, SQLite) using JDBC.
2. **Dynamic Job Creation:** Allow users to add new custom job profiles from the console menu during runtime.
3. **Additional Rules:** Implement new rules adhering to `EligibilityRule` (e.g., `SpecificDegreeRule`, `CertificationRule`, `GapYearRule`).
4. **Batch Evaluation & CSV Export:** Evaluate all registered candidates against a job in bulk and export an eligibility report.

---

## 12. Git & GitHub Commands

To manage and push your project to GitHub, execute the following commands in order:

1. **Check status of modified and new files:**
   ```bash
   git status
   ```
2. **Stage all changes:**
   ```bash
   git add .
   ```
3. **Commit the changes with a clear message:**
   ```bash
   git commit -m "Implement Placement Eligibility Decision Engine with Core Java OOP"
   ```
4. **Push commits to your GitHub remote repository:**
   ```bash
   git push -u origin main
   ```
   *(For subsequent pushes, you can simply run `git push`)*

---

## 13. Technical Interview Preparation (KodNest Module 6)

### Q1: What is the difference between an Abstract Class and an Interface in Java?
* **Answer:** An **interface** defines a pure contract ("what to do") with public abstract methods (prior to Java 8) and constants. It supports full multiple inheritance of type. An **abstract class** represents an incomplete class ("is-a" relationship) that can maintain state (instance variables), constructors, and both abstract and concrete methods. In our project, `EligibilityRule` is an interface because different rules share no common state—only a common behavior contract (`isSatisfied()` and `getFailureReason()`).

### Q2: Why did we declare fields `private` in `Candidate` and `Job`?
* **Answer:** To achieve **Encapsulation** and **Data Hiding**. Declaring fields `private` prevents external classes from modifying the internal state arbitrarily (e.g., assigning a negative percentage or 100 backlogs). State changes are regulated through constructor validation, and access is granted only via public getters.

### Q3: How is Polymorphism achieved in `EligibilityEvaluator`?
* **Answer:** The evaluator maintains a `List<EligibilityRule>`. When iterating through this list, it invokes `rule.isSatisfied(candidate, job)`. Even though the reference variable is of type `EligibilityRule`, the JVM uses **Dynamic Method Dispatch** at runtime to execute the overridden method of the actual instantiated object (`PercentageRule`, `BacklogRule`, or `GraduationYearRule`).

### Q4: Why doesn't the decision engine stop evaluating upon encountering the first failure?
* **Answer:** From a candidate and recruiter perspective, knowing *all* reasons for ineligibility is critical so the candidate knows every requirement they need to address, rather than discovering them one by one. The evaluator iterates through every rule and aggregates all failures into a `List<String>`.