package models;

import java.util.Date;

public class Attendance {

    private int id;
    private int studentId;
    private int teacherId;
    private Date date;
    private AttendanceStatusEnum status;
    private String comment; // Adding comments due to absence (Optionally)

    public Attendance(int id, int studentId, int teacherId, Date date, AttendanceStatusEnum status, String comment) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.date = date;
        this.status = status;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public AttendanceStatusEnum getStatus() {
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
