package service;

import models.Student;

public class StudentService {

    // Searching for similar students info to prevent errors if users have same data(duplicates)
    public boolean matchesFullProfile(Student s1, Student s2) {
        if (s1 == null || s2 == null) return false;
        return s1.getId() == s2.getId() &&
                s1.getFirstName().equals(s2.getFirstName()) &&
                s1.getLastName().equals(s2.getLastName()) &&
                s1.getPatronymic().equals(s2.getPatronymic()) &&
                s1.getEmail().equals(s2.getEmail()) &&
                s1.getPhoneNumber().equals(s2.getPhoneNumber());
    }
}
