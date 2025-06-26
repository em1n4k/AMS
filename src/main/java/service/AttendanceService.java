package service;

import models.Attendance;

public class AttendanceService {

    // Searching for similar student&teacher ID's and date to prevent duplicates in DB
    public boolean matchesFullProfile(Attendance att1, Attendance att2) {
        if (att1 == null || att2 == null) return false;
        return  att1.getStudentId() == att2.getStudentId() &&
                att1.getTeacherId() == att2.getTeacherId() &&
                att1.getSubjectId() == att2.getSubjectId() &&
                att1.getDate() == att2.getDate();
    }

    // Checking for a match by record ID (the same record)
    public boolean isSameEntity(Attendance att1, Attendance att2) {
        if (att1 == null || att2 == null) return false;
        return att1.getId() == att2.getId();
    }

    // Updating data when the ID matches
    public void updateDetailsFrom(Attendance target, Attendance source) {
        if (target == null || source == null || target.getId() != source.getId()) {
            throw new IllegalArgumentException("The attendance ID does not match!");
        }
        target.setStudentId(source.getStudentId());
        target.setTeacherId(source.getTeacherId());
        target.setSubjectId(source.getSubjectId());
        target.setDate(source.getDate());
        target.setStatus(source.getStatus());
        target.setComment(source.getComment());
    }
}
