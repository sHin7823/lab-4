package usecase;

import api.GradeDataBase;
import entity.Grade;
import entity.Team;

/**
 * GetAverageGradeUseCase class.
 */
public final class GetAverageGradeUseCase {
    private final GradeDataBase gradeDataBase;

    public GetAverageGradeUseCase(GradeDataBase gradeDataBase) {
        this.gradeDataBase = gradeDataBase;
    }

    /**
     * Get the average grade for a course across your team.
     * @param course The course.
     * @return The average grade.
     */
    public float getAverageGrade(String course) {
        // Call the API to get usernames of all your team members
        float sum = 0;
        int count = 0;
        // TODO: Q2
        final Team team = gradeDataBase.getMyTeam();
        // Call the API to get all the grades for the course for all your team members
        // TODO: Q1 Why say getGrades might be useful for this
        //  when theres a dedicated method to locate grades for a specific course
        String[] members = team.getMembers();
        for (String member : members) {
            Grade[] grades = gradeDataBase.getGrades(member);
            for (Grade grade : grades) {
                System.out.println(grade.getCourse());
                if (grade.getCourse().equals(course)) {
                    sum += grade.getGrade();
                    count++;
                    break;
                }
            }
        }

        if (count == 0) {
            return 0;
        }
        return sum / count;
    }
}
