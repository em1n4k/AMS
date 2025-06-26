package util;

import models.*;

public class ValidationUtil {

    public static boolean isValidStudent(Student student) {
        if (student == null) return false;
        return notBlank(student.getFirstName()) &&
                notBlank(student.getLastName()) &&
                notBlank(student.getPatronymic()) &&
                notBlank(student.getFacultyNumber()) &&
                notBlank(student.getEmail()) &&
                notBlank(student.getPhoneNumber()) &&
                student.getAge() >= 16 && student.getAge() <= 100 &&
                notBlank(student.getPassword());
    }

    public static boolean isValidAdmin (Admin admin) {
        if (admin == null) return false;
        return notBlank(admin.getUsername()) &&
                notBlank(admin.getPassword());
    }

    public static boolean isValidTeacher(Teacher teacher) {
        if (teacher == null) return false;
        return notBlank(teacher.getFirstName()) &&
                notBlank(teacher.getLastName()) &&
                notBlank(teacher.getPatronymic()) &&
                notBlank(teacher.getEmail()) &&
                notBlank(teacher.getPhoneNumber()) &&
                notBlank(teacher.getPassword());
    }

    public static boolean isValidAttendance(Attendance att) {
        if (att == null) return false;
        return att.getStudentId() > 0 &&
                att.getTeacherId() > 0 &&
                att.getDate() != null &&
                att.getStatus() != null;
    }


    private static boolean notBlank(String str) {
        return str != null && !str.isEmpty();
    }
}
