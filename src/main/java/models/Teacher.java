package models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Teacher {

    private long id;

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    @NotBlank(message = "Отчество обязательно")
    private String patronymic;

    private LocalDate birthDate;

    @NotBlank(message = "Название предмета обязательно")
    private String subject;

    @Positive(message = "Зарплата не может быть отрицательной или нулём")
    private BigDecimal salary;

    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;

    @NotBlank(message = "Пароль обязателен")
    private String password;

    public Teacher(long id, String firstName, String lastName, String patronymic, LocalDate birthDate, String email, String subject, String phoneNumber, BigDecimal salary , String password) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.salary = salary;
        this.password = password;
    }

    // no-arg конструктор
    public Teacher () {}

    public Teacher(long id, String firstName, String lastName, String patronymic, LocalDate birthDate, String email, String subject, String phoneNumber, String password) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", patronymic='" + patronymic + '\'' + ", birthDate='" + '\'' + ", subject='" + subject + '\'' + ", salary=" + salary + ", email='" + email + '\'' + ", phoneNumber=" + phoneNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Teacher teacher = (Teacher) obj;
        return id == teacher.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ").append(lastName);
        if (patronymic != null && !patronymic.isBlank()) {
            sb.append(" ").append(patronymic);
        }
        return sb.toString();
    }
}
