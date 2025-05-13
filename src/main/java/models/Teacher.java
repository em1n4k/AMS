package models;

import java.math.BigDecimal;

public class Teacher {

    private long id;
    private String firstName;
    private String lastName;
    private String subject;
    private BigDecimal salary;
    private String email;
    private String phoneNumber;

    public Teacher(long id, String firstName, String lastName, String email, String subject, String phoneNumber, BigDecimal salary) {

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.subject = subject;
        this.salary = salary;
    }

    public Teacher () {}

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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = this.phoneNumber;
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
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", subject='" + subject + '\'' + ", salary=" + salary + '}';
    }


}
