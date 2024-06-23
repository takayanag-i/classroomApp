package jp.co.collasho.classroom.common;

public enum DayOfWeek {
    MON("Mon", "月"), TUE("Tue", "火"), WED("Wed", "水"), THU("Thu", "木"), FRI("Fri", "金"), SAT("Sat",
            "土"), SUN("Sun", "日");

    private final String abbreviation;
    private final String japanese;

    DayOfWeek(String abbreviation, String japanese) {
        this.abbreviation = abbreviation;
        this.japanese = japanese;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public String getJapanese() {
        return japanese;
    }

    public static DayOfWeek fromAbbreviation(String abbreviation) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getAbbreviation().equals(abbreviation)) {
                return day;
            }
        }
        throw new IllegalArgumentException(abbreviation + "は曜日型にできません");
    }
}
