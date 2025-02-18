package models;

import java.time.LocalDate;

public class Attendance {

    private long id;
    private long studentId;
    private long teacherId;
    private long subjectId;
    private LocalDate date;
    private AttendanceStatusEnum.Status status;
    private String comment; // Adding comments due to absence (Optionally)

    public Attendance(long id, long studentId, long teacherId, long subjectId, LocalDate date, AttendanceStatusEnum.Status status, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.date = date;
        this.status = status;
        this.comment = comment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(long teacherId) {
        this.teacherId = teacherId;
    }

    public long getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(long subjectId) {
        this.subjectId = subjectId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AttendanceStatusEnum.Status getStatus() {
        return status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public String toString() {
        return "Attendance{" + "id=" + id + ", studentId=" + studentId + ", teacherId=" + teacherId + ", date=" + date + ", status=" + status + ", comment=" + comment + '\'' + '}';
    }
}
