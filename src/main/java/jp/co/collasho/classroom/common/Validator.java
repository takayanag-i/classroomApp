package jp.co.collasho.classroom.common;

import jp.co.collasho.classroom.exception.InvalidInputException;

public class Validator {
    // 出席番号のバリデーション
    public static void checkStudentId(String studentId) throws InvalidInputException {
        if (studentId == null || "".equals(studentId)) {
            throw new InvalidInputException("IDを入力してください。");
        }
        if (!studentId.matches("\\d{4}")) {
            throw new InvalidInputException("IDは4桁の半角数字で入力してください。");
        }
    }

    // 名前のバリデーション
    public static void checkName(String name) throws InvalidInputException {
        if (name == null || "".equals(name)) {
            throw new InvalidInputException("名前を入力してください。");
        }
        if (name.length() > 15) {
            throw new InvalidInputException("名前は15文字以内で入力してください。");
        }
    }

    // メールアドレスのバリデーション
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

    // パスワードのバリデーション
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

    // 曜日のバリデーション
    public static void checkDay(String day) throws InvalidInputException {
        if ("".equals(day)) {
            return;
        }
        if (day == null) {
            throw new RuntimeException("曜日の入力で予期しないnullが発生しました。");
        }
        if (!day.matches("[1-5]")) {
            throw new InvalidInputException("曜日は1から5の半角数字で入力してください。");
        }
    }

    // 時限のバリデーション
    public static void checkPeriod(String period) throws InvalidInputException {
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

    // 講座コードのバリデーション
    public static void checkCourseId(String courseId) throws InvalidInputException {
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

    // 講座名のバリデーション
    public static void checkCourseName(String courseName) throws InvalidInputException {
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

    // 担当教員名のバリデーション
    public static void checkInstructorName(String instructorName) throws InvalidInputException {
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
}
