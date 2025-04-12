package models;

import jakarta.validation.constraints.*;
import java.util.regex.Pattern;

public class Student {

    @Positive(message = "ID должен быть положительным")
    private long id;

    @NotBlank(message = "Имя обязательно")
    @jakarta.validation.constraints.Pattern(regexp = "[А-Яа-яЁёA-Za-z-'\\s]{2,50}", message = "Недопустимые символы в имени")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    @jakarta.validation.constraints.Pattern(regexp = "[А-Яа-яЁёA-Za-z-'\\s]{2,50}", message = "Недопустимые символы в фамилии")
    private String lastName;

    @NotBlank(message = "Номер студенческого факультета обязателен")
    @jakarta.validation.constraints.Pattern(regexp = ".*", message = "Специальность и направление")
    private String facultyNumber;

    @NotNull(message = "Статус посещаемости обязателен")
    private AttendanceStatus attendance;

    @NotBlank(message = "Год обучения обязателен")
    @jakarta.validation.constraints.Pattern(regexp = "^\\d{4}-\\d{4}$", message = "Формат: YYYY-YYYY")
    private String studyYear;

    @Min(value = 16, message = "Минимальный возраст: 16")
    @Max(value = 100, message = "Максимальный возраст: 75")
    private int age;

    @NotBlank(message = "Email обязателен")
    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Телефон обязателен")
    @jakarta.validation.constraints.Pattern(regexp = "^\\+?7\\d{10}$", message = "Формат: +7XXXXXXXXXX или 8XXXXXXXXXX")
    private String phoneNumber;

    // Сеттер для телефона с унификацией формата
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.startsWith("8")) {
            this.phoneNumber = "+7" + phoneNumber.substring(1);
        } else {
            this.phoneNumber = phoneNumber;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public AttendanceStatus getAttendance() {
        return attendance;
    }

    public void setAttendance(AttendanceStatus attendance) {
        if (!attendance.equals("П/Присутствие") && !attendance.equals("О/Отсутствие") && !attendance.equals("УП/Уважительная причина") && !attendance.equals("Б/Болезнь")) {
            throw new IllegalArgumentException("Установите одно из значений: П/Присутствие, О/Отсутствие, УП/Уважительная причина, Б/Болезнь");
        }
        this.attendance = attendance;
    }
}