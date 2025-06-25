package service;

import models.Teacher;

public class TeacherService {

    // Searching for similar teachers info to prevent errors if users have same data(duplicates)
    public boolean matchesFullProfile(Teacher t1, Teacher t2) {
        if (t1 == null || t2 == null) return false;
        return t1.getId() == t2.getId() &&
                t1.getFirstName().equals(t2.getFirstName()) &&
                t1.getLastName().equals(t2.getLastName()) &&
                t1.getPatronymic().equals(t2.getPatronymic()) &&
                t1.getEmail().equals(t2.getEmail());
    }
}
