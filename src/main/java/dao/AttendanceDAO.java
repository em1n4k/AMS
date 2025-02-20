package dao;

import database.DatabaseConnection;
import models.Attendance;
import models.AttendanceStatus;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {

    public List<Attendance> getAttendanceByStudentAndSubject(long studentId, long subjectId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT id, student_id, teacher_id, date, status, comment " + "FROM attendance WHERE student_id = ? AND subject_id = ? " + "ORDER BY date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, studentId);
            statement.setLong(2, subjectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                attendanceList.add(new Attendance(
                        resultSet.getLong("id"),
                        resultSet.getLong("student_id"),
                        resultSet.getLong("teacher_id"),
                        resultSet.getLong("subject_id"),
                        resultSet.getDate("date").toLocalDate(),
                        AttendanceStatus.valueOfStatus(resultSet.getString("status")),
                        resultSet.getString("comment")
                ));
            }
        } catch (SQLException e) {
            System.err.println("Произошла ошибка при получении посещаемости студента " + studentId + " по предмету " + subjectId + ": " + e.getMessage());
        }
        return attendanceList;
    }
}

