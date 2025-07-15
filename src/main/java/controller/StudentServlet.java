package controller;

import models.Student;
import dao.StudentDAO;

import java.io.IOException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;

@WebServlet(name = "StudentServlet", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        
        // Перенаправление на student.jsp
        request.getRequestDispatcher("/WEB-INF/pages/student.jsp").forward(request, response);

    }

}