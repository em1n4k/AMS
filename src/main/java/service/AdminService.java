package service;

import models.Admin;

public class AdminService {

    // Searching for similar admins passwords and usernames to prevent errors if users have same data(duplicates)
    public boolean matchesFullProfile(Admin a1, Admin a2) {
        if (a1 == null || a2 == null) return false;
        return a1.getId() == a2.getId() &&
                a1.getUsername().equals(a2.getUsername()) &&
                a1.getPassword().equals(a2.getPassword());
    }
}
