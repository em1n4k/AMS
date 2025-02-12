package Models;

import java.util.Date;

public class AttendanceStatusControl {

    private int id;
    private int studentId;
    private int teacherId;
    private Date date;
    private AttendanceStatusEnum status;

    public AttendanceStatusControl(int id, int studentId, int teacherId, Date date, AttendanceStatusEnum status) {
        this.id = id;
        this.studentId = studentId;
        this.teacherId = teacherId;
        this.date = date;
        this.status = status;
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
}
