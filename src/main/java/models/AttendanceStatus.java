package models;

public enum AttendanceStatus {
    PRESENT ("Присутствовал"),
    ABSENT ("Отсутствовал"),
    VALID_REASON ("Уважительная причина"),
    LATE ("Опоздал");

    private final String displayName;

    AttendanceStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AttendanceStatus valueOfStatus(String status) {
        try {
            return AttendanceStatus.valueOf(status.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Неверное значение статуса: " + status);
        }
    }
}