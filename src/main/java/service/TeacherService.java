package service;

import models.Teacher;

public class TeacherService {

    // Comparison of the full profile without id
    public boolean matchesFullProfile(Teacher t1, Teacher t2) {
        if (t1 == null || t2 == null) return false;
        return t1.getFirstName().equals(t2.getFirstName()) &&
                t1.getLastName().equals(t2.getLastName()) &&
                safeEquals(t1.getPatronymic(), t2.getPatronymic()) &&
                t1.getSubject().equals(t2.getSubject()) &&
                t1.getEmail().equals(t2.getEmail()) &&
                t1.getPhoneNumber().equals(t2.getPhoneNumber());
    }

    // Comparison by full name + subject (without email and phone number)
    public boolean matchesNameAndSubject(Teacher t1, Teacher t2) {
        if (t1 == null || t2 == null) return false;
        return t1.getFirstName().equals(t2.getFirstName())
                && t1.getLastName().equals(t2.getLastName())
                && safeEquals(t1.getPatronymic(), t2.getPatronymic())
                && t1.getSubject().equals(t2.getSubject());
    }

    // Checking by id (whether there is one entity)
    public boolean isSameEntity(Teacher t1, Teacher t2) {
        if (t1 == null || t2 == null) return false;
        return t1.getId() == t2.getId();
    }

    // Updating the data of one teacher from another by matching the id
    public void updateDetailsFrom(Teacher target, Teacher source) {
        if (target == null || source == null || target.getId() != source.getId()) {
            throw new IllegalArgumentException("The teacher ID does not match!");
        }
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setPatronymic(source.getPatronymic());
        target.setSubject(source.getSubject());
        target.setEmail(source.getEmail());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setSalary(source.getSalary());
        target.setPassword(source.getPassword());
    }


    // Utility method for comparing null strings
    private boolean safeEquals(String s1, String s2) {
        if (s1 == null && s2 == null) return false;
        if (s1 == null || s2 == null) return false;
        return s1.equals(s2);
    }

}

