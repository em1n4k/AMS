package models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

public class Student {

    private long id;

    @NotBlank(message = "The name is required")
    private String firstName;

    @NotBlank(message = "The surname is required")
    private String lastName;

    @NotBlank(message = "Patronymic required")
    private String patronymic;

    private LocalDate birthDate;

    @NotBlank
    private String password;

    @Email(message = "Incorrect email address")
    private String email;

    @NotBlank(message = "Faculty number is required")
    private String facultyNumber;

    private AttendanceStatus attendanceStatus;

    @NotBlank(message = "A phone number is required")
    private String phoneNumber;

    public Student(long id, String firstName, String lastName, String patronymic, LocalDate birthDate, String email, String password, String facultyNumber, AttendanceStatus attendanceStatus, String phoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.facultyNumber = facultyNumber;
        this.attendanceStatus = attendanceStatus;
        this.phoneNumber = phoneNumber;

    }

    // no-arg конструктор
    public Student() {}

    public Student(long id, String firstName, String lastName, String patronymic, LocalDate birthDate, String email, String password, String facultyNumber, String phoneNumber) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
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



    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getAge() {
        return Period.between(birthDate, LocalDate.now()).getYears();
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
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", patronymic='" + patronymic + '\'' + ", attendanceStatus='" + attendanceStatus + ", birthDate='" + birthDate + ", email='" + email + '\'' + ", facultyNumber='" + facultyNumber + '\'' + ", phoneNumber='" + phoneNumber + '}';
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

    public String getFullName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName).append(" ").append(lastName);
        if (patronymic != null && !patronymic.isBlank()) {
            sb.append(" ").append(patronymic);
        }
        return sb.toString();
    }

}