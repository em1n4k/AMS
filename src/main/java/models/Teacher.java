package models;

import java.math.BigDecimal;
import java.util.Objects;

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

    // no-arg конструктор
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
                "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", subject='" + subject + '\'' + ", salary=" + salary + '}';
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

    // Data similarity
    public boolean dataSimilarity (Teacher similarData) {
        if (similarData == null) return false;
        return firstName.equals(similarData.firstName) && lastName.equals(similarData.lastName) && subject.equals(similarData.subject) && email.equals(similarData.email) && phoneNumber.equals(similarData.phoneNumber);
    }

    // Searching for similar teachers name, surname and subject to prevent errors if users have same data
    public boolean hasSameNameSurnameSubject (Teacher sameNameSurnameSubject) {
        if (sameNameSurnameSubject == null) return false;
        return firstName.equals(sameNameSurnameSubject.firstName) && lastName.equals(sameNameSurnameSubject.lastName) && subject.equals(sameNameSurnameSubject.subject);
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
}
