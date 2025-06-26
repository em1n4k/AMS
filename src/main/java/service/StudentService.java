package service;

import models.Student;

public class StudentService {

    // Searching for similar students info to prevent errors if users have same data(duplicates)
    public boolean matchesFullProfile(Student st1, Student st2) {
        if (st1 == null || st2 == null) return false;
        return  st1.getFirstName().equals(st2.getFirstName()) &&
                st1.getLastName().equals(st2.getLastName()) &&
                st1.getPatronymic().equals(st2.getPatronymic()) &&
                st1.getEmail().equals(st2.getEmail()) &&
                st1.getPhoneNumber().equals(st2.getPhoneNumber());
    }

    // Searching for similar students full name and its subject to prevent errors if users have same data
    public boolean matchesNameAndSubject(Student st1, Student st2) {
        if (st1 == null || st2 == null) return false;
        return st1.getFirstName().equals(st2.getFirstName()) &&
                st1.getLastName().equals(st2.getLastName()) &&
                st1.getPatronymic().equals(st2.getPatronymic()) &&
                st1.getFacultyNumber().equals(st2.getFacultyNumber());
    }

    // Updating data if Student objects have similar ID's
    public void updateDetailsFrom(Student target, Student source) {
        if (target == null || source == null || target.getId() != source.getId()) {
            throw new IllegalArgumentException("The student ID does not match!");
        }
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setPatronymic(source.getPatronymic());
        target.setAge(source.getAge());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setFacultyNumber(source.getFacultyNumber());
        target.setAttendanceStatus(source.getAttendanceStatus());
    }
}
