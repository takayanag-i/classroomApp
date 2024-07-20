package jp.co.collasho.classroom.common;

/**
 * 曜日の列挙子
 */
public enum DayOfWeek {
    MON("1", "月"), TUE("2", "火"), WED("3", "水"), THU("4", "木"), FRI("5", "金"), UNSET("", "");

    /** 略称 */
    private final String num;
    /** 漢字 */
    private final String japanese;

    /**
     * コンストラクタ
     * 
     * @param num 番号
     * @param japanese 漢字
     */
    DayOfWeek(String num, String japanese) {
        this.num = num;
        this.japanese = japanese;
    }

    /*
     * ゲッタ(インスタンス→番号・漢字)
     */
    public String getNum() {
        return num;
    }

    public String getJapanese() {
        return japanese;
    }

    /**
     * 曜日番号をint型で返す
     * 
     * @return 番号 int
     */
    public int getInt() {
        return Integer.parseInt(num);
    }

    /**
     * str文字列→インスタンス
     * 
     * @param str 番号 String
     * @return 曜日型
     */
    public static DayOfWeek fromNum(String str) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(str + "は曜日型にできません");
    }

    /**
     * int文字列→インスタンス
     * 
     * @param num 番号 int
     * @return 曜日型
     */
    public static DayOfWeek fromNum(int num) {

        String str = String.valueOf(num);

        for (DayOfWeek day : DayOfWeek.values()) {
            if (day.getNum().equals(str)) {
                return day;
            }
        }

        throw new IllegalArgumentException(num + "は曜日型にできません");
    }

    /**
     * 漢字→インスタンス
     * 
     * @param japanese 漢字
     * @return 曜日型
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
