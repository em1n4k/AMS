package models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class Student {

    private long id;

    @NotBlank(message = "The name is required")
    private String firstName;

    @NotBlank(message = "The surname is required")
    private String lastName;

    @NotBlank(message = "Patronymic required")
    private String patronymic;

    @Min(value = 16, message = "Minimum age: 16 years")
    @Max(value = 100, message = "Maximum age: 100 years")
    private int age;

    @NotBlank
    private String password;

    @Email(message = "Incorrect email address")
    private String email;

    @NotBlank(message = "Faculty number is required")
    private String facultyNumber;

    private AttendanceStatus attendanceStatus;

    @NotBlank(message = "A phone number is required")
    private String phoneNumber;

    public Student(long id, String firstName, String lastName, String patronymic, int age, String email, String password, String facultyNumber, AttendanceStatus attendanceStatus, String phoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.age = age;
        this.email = email;
        this.password = password;
        this.facultyNumber = facultyNumber;
        this.attendanceStatus = attendanceStatus;
        this.phoneNumber = phoneNumber;

    }

    // no-arg конструктор
    public Student() {}

    public Student(long id, String firstName, String lastName, String patronymic, int age, String email, String password, String facultyNumber, String phoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.age = age;
        this.password = password;
        this.email = email;
        this.password = password;
        this.facultyNumber = facultyNumber;
        this.phoneNumber = phoneNumber;
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

    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public AttendanceStatus getAttendanceStatus() {
        return attendanceStatus;
    }

    public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
        this.attendanceStatus = attendanceStatus;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", patronymic='" + patronymic + '\'' + ", attendanceStatus='" + attendanceStatus + ", age='" + age + ", email='" + email + '\'' + ", facultyNumber='" + facultyNumber + '\'' + ", phoneNumber='" + phoneNumber + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id == student.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    // Data validation before initialization
    public boolean isValid() {
        return firstName != null && !firstName.isBlank() && lastName != null && !lastName.isBlank() && patronymic != null && !patronymic.isBlank() && facultyNumber != null && !facultyNumber.isBlank() && email != null && !email.isBlank() && phoneNumber != null && !phoneNumber.isBlank();
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