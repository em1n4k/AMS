package models;

import java.util.Objects;

public class Admin {

    private long id;
    private String username;
    private String password;

    public Admin() {}

    public Admin(long id, String username, String password) {

        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters & Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Admin admin = (Admin) obj;
        return id == admin.id && username.equals(admin.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }

    // Data validation before initialization
    public boolean isValid() {
        return username != null && !username.isBlank() && password != null && !password.isBlank();
    }

    // Searching for similar admins passwords and usernames to prevent errors if users have same data
    public boolean matchesFullProfile(Admin other) {
        if (other == null) return false;
        return this.id == other.id &&
                this.username.equals(other.username) &&
                this.password.equals(other.password);
    }
}
