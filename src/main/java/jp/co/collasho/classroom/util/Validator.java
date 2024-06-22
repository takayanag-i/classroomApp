package jp.co.collasho.classroom.util;

import jp.co.collasho.classroom.exception.ValidationError;

public class Validator {
    public static void checkUserId(String userId) throws ValidationError {
        if (userId == null || "".equals(userId)) {
            throw new ValidationError("IDを入力してください。");
        }
        // その他のチェック 文字数など
    }

    public static void checkName(String userId) throws ValidationError {
        // チェック
    }

    public static void checkEmail(String email) throws ValidationError {
        // チェック
    }

    public static void checkPassword(String password) throws ValidationError {
        // チェック
    }
}
