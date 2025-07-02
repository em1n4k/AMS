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

    // ID verification
    public boolean isSameEntity(Admin a1, Admin a2) {
        if (a1 == null || a2 == null) return false;
        return a1.getId() == a2.getId();
    }

    // Updating data by matching id
    public void updateDetailsFrom(Admin target, Admin source) {
        if (target == null || source == null || target.getId() != source.getId()) {
            throw new IllegalArgumentException("The admin ID does not match!");
        }
        target.setUsername(source.getUsername());
        target.setPassword(source.getPassword());
    }
}
