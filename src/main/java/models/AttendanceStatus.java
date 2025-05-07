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

    public static AttendanceStatus valueOfStatus(String status) {
        return null;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static AttendanceStatus fromDisplayName(String displayName) {
        for (AttendanceStatus status : values()) {
            if (status.displayName.equals(displayName)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Неверный статус " + displayName);
    }
}