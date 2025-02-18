class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String courseName;
    private String prerequisite;
    private int maxStudents;
    private int enrolledStudents;

    public Course(String courseName, String prerequisite, int maxStudents) {
        this.courseName = courseName;
        this.prerequisite = prerequisite;
        this.maxStudents = maxStudents;
        this.enrolledStudents = 0;
    }

    public void enrollStudent(String studentName, boolean hasCompletedPrerequisite) throws CourseFullException, PrerequisiteNotMetException {
        if (!hasCompletedPrerequisite) {
            throw new PrerequisiteNotMetException("Complete " + prerequisite + " before enrolling in " + courseName + ".");
        }
        if (enrolledStudents >= maxStudents) {
            throw new CourseFullException("Course " + courseName + " is full. No more enrollments allowed.");
        }
        enrolledStudents++;
        System.out.println(studentName + " successfully enrolled in " + courseName);
    }
}

public class UniversityEnrollment {
    public static void main(String[] args) {
        Course advancedJava = new Course("Advanced Java", "Core Java", 2);

        try {
            advancedJava.enrollStudent("Prateek", false);
        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        try {
            advancedJava.enrollStudent("Purwa", true);
            advancedJava.enrollStudent("Rano", true);
            advancedJava.enrollStudent("Prince", true);
        } catch (PrerequisiteNotMetException | CourseFullException e) {
            System.out.println("Error: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }
    }
}

