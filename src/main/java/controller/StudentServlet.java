package controller;

import models.Student;
import dao.StudentDAO;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "StudentServlet", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        // Инициализация DAO и объявление тестовых данных
        studentDAO = new StudentDAO();
        studentDAO.add(new Student(1, "Материй", "Силоуков", 18 ,"matsil@gmail.com", "42.03.01", null, "+7(999)941-52-52" ));
        studentDAO.add(new Student(2, "Мустафа", "Ахмедов", 18 ,"mustafa@mail.ru", "42.03.01", null, "+7(999)952-23-24" ));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        // Передача списка студентов в request
        request.setAttribute("students", studentDAO.getAll());

        // Перенаправление на student.jsp
        request.getRequestDispatcher("/WEB-INF/pages/student.jsp").forward(request, response);

    }



}