package jp.co.collasho.classroom.common;

import jp.co.collasho.classroom.exception.InvalidInputException;

public class Validator {
    public static void checkStudentId(String studentId) throws InvalidInputException {
        if (studentId == null || "".equals(studentId)) {
            throw new InvalidInputException("IDを入力してください。");
        }
        // その他のチェック 文字数など
    }

    public static void checkName(String userId) throws InvalidInputException {
        // チェック
    }

    public static void checkEmail(String email) throws InvalidInputException {
        // チェック
    }

    public static void checkPassword(String password) throws InvalidInputException {
        // チェック
    }
}
