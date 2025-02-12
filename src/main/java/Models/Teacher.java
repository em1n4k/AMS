package Models;

public class Teacher {

    private String firstName;
    private String lastName;
    private int id;
    private String attendance;
    private String subject;
    private float salary;

    public Teacher(int id, String firstName, String lastName, int age, String facultyNumber, String attendance) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.attendance = attendance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getAttendance() {
        return attendance;
    }



}
