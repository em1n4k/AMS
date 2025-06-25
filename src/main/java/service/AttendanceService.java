package service;

import models.Attendance;

public class AttendanceService {

    // Searching for similar student&teacher ID's and date to prevent duplicates in DB
    public boolean matchesFullProfile(Attendance att1, Attendance att2) {
        if (att1 == null || att2 == null) return false;
        return  att1.getStudentId() == att2.getStudentId() &&
                att1.getTeacherId() == att2.getTeacherId() &&
                att1.getDate() == att2.getDate();
    }
}
