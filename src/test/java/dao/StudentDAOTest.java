package dao;

import dao.StudentDAO;
import models.Student;

import java.time.LocalDate;

public class StudentDAOTest {
    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAO();

        // Creating a test student
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Sharp");
        student.setPatronymic("Cristoph");
        student.setBirthDate(LocalDate.of(2004, 1, 15));
        student.setPassword("qwerty123321");
        student.setEmail("student@gmail.com");
        student.setFacultyNumber("01-522-333");
        student.setPhoneNumber("+7(999)941-51-23");

        // Pulling data into DB
        studentDAO.add(student);
    }
}
