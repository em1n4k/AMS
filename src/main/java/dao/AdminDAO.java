package dao;

import models.Admin;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Objects;

public class AdminDAO implements BaseDAO<Admin> {

    private List<Admin> adminList = new ArrayList<>();

    public AdminDAO() {}

    @Override
    public Optional<Admin> getById(int id) {
        return Optional.ofNullable(adminList.get((int) id));
    }

    @Override
    public List<Admin> getAll() {
        return adminList;
    }

    @Override
    public void add(Admin admin) {
        adminList.add(admin);
    }

    @Override
    public void update(Admin admin, String[] params) {

        Objects.requireNonNull(params[0], "Имя пользователя не может быть null");
        Objects.requireNonNull(params[1], "Пароль не может быть null");

        admin.setUsername(params[0]);
        admin.setPassword(params[1]);
    }

    @Override
    public void delete(Admin admin) {
        adminList.remove(admin);
    }
}
