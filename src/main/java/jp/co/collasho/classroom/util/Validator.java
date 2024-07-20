package jp.co.collasho.classroom.util;

import jp.co.collasho.classroom.constants.ErrorMessages;
import jp.co.collasho.classroom.exception.InvalidInputException;

/**
 * バリデータクラス
 */
public class Validator {

    private Validator() {
        throw new IllegalStateException(ErrorMessages.UTIL_NEW_ERROR);
    }

    /**
     * 出席番号のバリデーションチェック
     * 
     * @param studentId 出席番号
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkStudentId(String studentId) throws InvalidInputException {
        if (studentId == null || "".equals(studentId)) {
            throw new InvalidInputException("IDを入力してください。");
        }
        if (!studentId.matches("\\d{4}")) {
            throw new InvalidInputException("IDは4桁の半角数字で入力してください。");
        }
    }

    /**
     * 名前のバリデーションチェック
     * 
     * @param name 名前
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkName(String name) throws InvalidInputException {
        if (name == null || "".equals(name)) {
            throw new InvalidInputException("名前を入力してください。");
        }
        if (name.length() > 15) {
            throw new InvalidInputException("名前は15文字以内で入力してください。");
        }
    }

    /**
     * メールアドレスのバリデーションチェック
     * 
     * @param email メールアドレス
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkEmail(String email) throws InvalidInputException {
        if (email == null || "".equals(email)) {
            throw new InvalidInputException("メールアドレスを入力してください。");
        }
        if (email.length() > 63) {
            throw new InvalidInputException("メールアドレスは63文字以内で入力してください。");
        }
        if (!email.matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidInputException("有効なメールアドレスを入力してください。");
        }
    }

    /**
     * パスワードのバリデーションチェック
     * 
     * @param password パスワード
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkPassword(String password) throws InvalidInputException {
        if (password == null || "".equals(password)) {
            throw new InvalidInputException("パスワードを入力してください。");
        }
        if (password.length() > 63) {
            throw new InvalidInputException("パスワードは63文字以内で入力してください。");
        }
        if (!password.matches("[\\w!@#$%^&*()]+")) {
            throw new InvalidInputException("パスワードは半角英数および使用可能な半角記号(!@#$%^&*())で入力してください。");
        }
    }

    /**
     * 曜日のバリデーションチェック（検索用）
     * 
     * @param dayOfWeek 曜日番号
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkDayOfWeekForSearch(String dayOfWeek) throws InvalidInputException {
        if ("".equals(dayOfWeek)) {
            return;
        }
        if (dayOfWeek == null) {
            throw new RuntimeException("曜日の入力で予期しないnullが発生しました。");
        }
        if (!dayOfWeek.matches("[1-5]")) {
            throw new InvalidInputException("曜日は1から5の半角数字で入力してください。");
        }
    }

    /**
     * 時限のバリデーションチェック（検索用）
     * 
     * @param period 時限
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkPeriodForSearch(String period) throws InvalidInputException {
        if ("".equals(period)) {
            return;
        }
        if (period == null) {
            throw new RuntimeException("時限の入力で予期しないnullが発生しました。");
        }
        if (!period.matches("[1-5]")) {
            throw new InvalidInputException("時限は1から5の半角数字で入力してください。");
        }
    }

    /**
     * 講座IDのバリデーションチェック（検索用）
     * 
     * @param courseId 講座ID
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkCourseIdForSearch(String courseId) throws InvalidInputException {
        if ("".equals(courseId)) {
            return;
        }
        if (courseId == null) {
            throw new RuntimeException("講座コードの入力で予期しないnullが発生しました。");
        }
        if (!courseId.matches("[A-Za-z0-9]{5}")) {
            throw new InvalidInputException("講座コードは5字の半角英数字で入力してください。");
        }
    }

    /**
     * 講座名のバリデーションチェック（検索用）
     * 
     * @param courseName 講座名
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkCourseNameForSearch(String courseName) throws InvalidInputException {
        if ("".equals(courseName)) {
            return;
        }
        if (courseName == null || "".equals(courseName)) {
            throw new RuntimeException("講座名の入力で予期しないnullが発生しました。");
        }
        if (courseName.length() > 30) {
            throw new InvalidInputException("講座名は30字以内で入力してください。");
        }
    }

    /**
     * 担当教員名のバリデーションチェック（検索用）
     * 
     * @param instructorName 担当教員名
     * @throws InvalidInputException 不正な入力があったときにスローされる例外
     */
    public static void checkInstructorNameForSearch(String instructorName)
            throws InvalidInputException {
        if ("".equals(instructorName)) {
            return;
        }
        if (instructorName == null || "".equals(instructorName)) {
            throw new RuntimeException("担当教員名の入力で予期しないnullが発生しました。");
        }
        if (instructorName.length() > 15) {
            throw new InvalidInputException("担当教員名は15字以内で入力してください。");
        }
    }

    /**
     * 講座コードのバリデーションチェック（選択式）
     */
    public static void checkSelectedCourseId(String courseId) throws InvalidInputException {
        if (courseId == null || "".equals(courseId)) {
            throw new InvalidInputException("講座を選択してください。");
        }

        if (!courseId.matches("[A-Za-z0-9]{5}")) {
            throw new InvalidInputException("講座コードは5字の半角英数字で入力してください。");
        }
    }
}
