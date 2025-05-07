package dao;

import models.Student;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

public class StudentDAO implements BaseDAO<Student> {

    private List<Student> studentList = new ArrayList<>();

    public StudentDAO() {}

    @Override
    public Optional<Student> getById(int id) {
        return Optional.ofNullable(studentList.get((int) id));
    }

    @Override
    public List<Student> getAll() {
        return studentList;
    }

    @Override
    public void add(Student student) {
        studentList.add(student);
    }

    @Override
    public void update(Student student, String[] params) {

        Objects.requireNonNull(params[0], "Имя не может быть null");
        Objects.requireNonNull(params[1], "Фамилия не может быть null");
        Objects.requireNonNull(params[2], "Возраст не может быть null");
        Objects.requireNonNull(params[3], "E-mail не может быть null");
        Objects.requireNonNull(params[4], "Номер факультета не может быть null");

        student.setFirstName(params[0]);
        student.setLastName(params[1]);
        student.setAge(Integer.parseInt(params[2]));
        student.setEmail(params[3]);
        student.setFacultyNumber(params[4]);

    }

    @Override
    public void delete(Student student) {
        studentList.remove(student);
    }

}