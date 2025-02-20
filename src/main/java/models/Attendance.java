package models;

import java.time.LocalDate;

public class Attendance {

    private long id;
    private long studentId;
    private long teacherId;
    private long subjectId;
    private LocalDate date;
    private AttendanceStatus status;
    private String comment; // Adding comments due to absence (Optionally)

    public Attendance(long id, long studentId, long teacherId, long subjectId, LocalDate date, AttendanceStatus status, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.subjectId = subjectId;
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

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Attendance that = (Attendance) obj;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }


    @Override
    public String toString() {
        return "Attendance{" + "id=" + id + ", studentId=" + studentId + ", teacherId=" + teacherId + ", date=" + date + ", status=" + status + ", comment='" + (comment != null ? comment : "Нет комментария") + '\'' + '}';
    }
}
