package models;

public class Student {

    private String firstName;
    private String lastName;
    private String email;
    private long id;
    private String facultyNumber;
    private String attendance;

    public Student(long id, String firstName, String lastName, String email, String facultyNumber, String attendance) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.facultyNumber = facultyNumber;
        this.attendance = attendance;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getFacultyNumber() {
        return facultyNumber;
    }

    public void setFacultyNumber(String facultyNumber) {
        this.facultyNumber = facultyNumber;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        if (!attendance.equals("П/Присутствие") && !attendance.equals("О/Отсутствие") && !attendance.equals("УП/Уважительная причина") && !attendance.equals("Б/Болезнь")) {
            throw new IllegalArgumentException("Установите одно из значений: П/Присутствие, О/Отсутствие, УП/Уважительная причина, Б/Болезнь");
        }
        this.attendance = attendance;
    }


}
