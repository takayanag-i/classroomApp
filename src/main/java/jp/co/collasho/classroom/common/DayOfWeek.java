package jp.co.collasho.classroom.common;

/**
 * 曜日の列挙子
 */
public enum DayOfWeek {
    MON("Mon", "月"), TUE("Tue", "火"), WED("Wed", "水"), THU("Thu", "木"), FRI("Fri", "金"), SAT("Sat",
            "土"), SUN("Sun", "日"), UNSET("", "");

    /** 略称 */
    private final String abbreviation;
    /** 漢字 */
    private final String japanese;

    /**
     * コンストラクタ
     * 
     * @param abbreviation 略称
     * @param japanese 漢字
     */
    DayOfWeek(String abbreviation, String japanese) {
        this.abbreviation = abbreviation;
        this.japanese = japanese;
    }

    /*
     * ゲッタ(インスタンス→略称・漢字)
     */
    public String getAbbreviation() {
        return abbreviation;
    }

    public String getJapanese() {
        return japanese;
    }

    /**
     * 略称→インスタンス
     * 
     * @param abbreviation 略称
     * @return 曜日型
     */
    public static DayOfWeek fromAbbreviation(String abbreviation) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getAbbreviation().equals(abbreviation)) {
                return day;
            }
        }
        throw new IllegalArgumentException(abbreviation + "は曜日型にできません");
    }

    /**
     * 漢字→インスタンス
     * 
     * @param japanese 漢字
     * @return
     */
    public static DayOfWeek fromJapanese(String japanese) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getJapanese().equals(japanese)) {
                return day;
            }
        }
        throw new IllegalArgumentException(japanese + "は曜日型にできません");
    }
}
