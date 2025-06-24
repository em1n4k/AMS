package models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

public class Teacher {

    private long id;

    @NotBlank(message = "Имя обязательно")
    private String firstName;

    @NotBlank(message = "Фамилия обязательна")
    private String lastName;

    @NotBlank(message = "Отчество обязательно")
    private String patronymic;

    @NotBlank(message = "Название предмета обязательно")
    private String subject;

    @Positive(message = "Зарплата не может быть отрицательной или нулём")
    private BigDecimal salary;

    @Email(message = "Некорректный email")
    private String email;

    @NotBlank(message = "Номер телефона обязателен")
    private String phoneNumber;

    public Teacher(long id, String firstName, String lastName, String patronymic, String email, String subject, String phoneNumber, BigDecimal salary) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.salary = salary;
    }

    // no-arg конструктор
    public Teacher () {}

    public Teacher(long id, String firstName, String lastName, String patronymic, String email, String subject, String phoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", patronymic='" + patronymic + '\'' + ", subject='" + subject + '\'' + ", salary=" + salary + ", email='" + email + '\'' + ", phoneNumber=" + phoneNumber + '}';
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

    // Searching for similar teachers full name, contacts and its subject to prevent errors if users have same data
    public boolean matchesFullProfile(Teacher other) {
        if (other == null) return false;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName) && Objects.equals(patronymic, other.patronymic) && subject.equals(other.subject) && email.equals(other.email) && phoneNumber.equals(other.phoneNumber);
    }

    // Searching for similar teachers full name and its subject to prevent errors if users have same data
    public boolean matchesNameAndSubject (Teacher other) {
        if (other == null) return false;
        return firstName.equals(other.firstName) && lastName.equals(other.lastName) && Objects.equals(patronymic, other.patronymic) && subject.equals(other.subject);
    }

    // Updating data if Teacher objects have similar ID's
    public void updateDetailsFrom (Teacher sameId) {
        if (sameId == null || this.id != sameId.id) {
            throw new IllegalArgumentException("ID преподавателей не сходится!");
        }
        this.firstName = sameId.firstName;
        this.lastName = sameId.lastName;
        this.subject = sameId.subject;
        this.email = sameId.email;
        this.phoneNumber = sameId.phoneNumber;
        this.salary = sameId.salary;
    }

    // Data validating before initialization
    public boolean isValid() {
        return firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank() && subject != null && !subject.isBlank() && email != null && !email.isBlank() && phoneNumber != null && !phoneNumber.isBlank();
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
