package models;

public class AttendanceStatusEnum {

    public enum Status {
        PRESENT, // Присутствовал на занятии
        ABSENT, // Отсутствовал на занятии
        VALID_REASON
    }

    public static Status valueOfStatus(String status) {
        try {
            return Status.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверное значение статуса: " + status);
        }
    }
}
