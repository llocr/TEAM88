package model;

public class GradeCalculator {
    private static final int[] mandatoryThresholds = {95, 90, 80, 70, 60, 0};
    private static final Grade[] mandatoryGrades = {Grade.A, Grade.B, Grade.C, Grade.D, Grade.F, Grade.N};

    private static final int[] choiceThresholds = {90, 80, 70, 60, 50, 0};
    private static final Grade[] choiceGrades = {Grade.A, Grade.B, Grade.C, Grade.D, Grade.F, Grade.N};

    public static Grade calculateGrade(int score, SubjectType type) {
        int[] thresholds;
        Grade[] grades;

        if (type == SubjectType.MANDATORY) {
            thresholds = mandatoryThresholds;
            grades = mandatoryGrades;
        } else {
            thresholds = choiceThresholds;
            grades = choiceGrades;
        }

        for (int i = 0; i < thresholds.length; i++) {
            if (score >= thresholds[i]) {
                return grades[i];
            }
        }
        return Grade.N; // Default, should not happen
    }
}