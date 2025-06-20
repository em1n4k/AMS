package dao;

import models.Teacher;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

public class TeacherDAO implements BaseDAO<Teacher> {

    private List<Teacher> teacherList = new ArrayList<>();

    public TeacherDAO() {}

    @Override
    public Optional<Teacher> getById (int id) {
        return Optional.ofNullable(teacherList.get((int) id));
    }

    @Override
    public List<Teacher> getAll() {
        return teacherList;
    }

    @Override
    public void add(Teacher teacher) {
        teacherList.add(teacher);
    }

    @Override
    public void update(Teacher teacher, String[] params) {

        Objects.requireNonNull(params[0], "Имя не может быть null");
        Objects.requireNonNull(params[1], "Фамилия не может быть null");
        Objects.requireNonNull(params[2], "Отчество не может быть null");
        Objects.requireNonNull(params[3], "Название предмета не может быть null");
        Objects.requireNonNull(params[4], "E-mail не может быть null");

        teacher.setFirstName(params[0]);
        teacher.setLastName(params[1]);
        teacher.setPatronymic(params[2]);
        teacher.setSubject(params[3]);
        teacher.setEmail(params[4]);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherList.remove(teacher);
    }
}
