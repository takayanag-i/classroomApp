package jp.co.collasho.classroom.util;

import java.util.regex.Pattern;
import jp.co.collasho.classroom.exception.DaoException;

/**
 * MySQL由来のエラーに関するUtilクラス
 */
public class DbErrorUtil {

    /**
     * 主キー重複またはユニーク制約違反かどうかを判定する
     * 
     * @param e DaoException
     * @param key 判定したいキー テーブル名.PRIMARY または テーブル名.カラム名
     * @return true / false
     */
    public static boolean isDuplicateError(DaoException e, String key) {
        int code = e.getErrorCode();
        String message = e.getMessage();
        String regexFormat =
                "java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '.*?' for key '%s'";
        String regex = String.format(regexFormat, key);
        Pattern pattern = Pattern.compile(regex);

        if (code == 1062 && pattern.matcher(message).matches()) {
            return true;
        } else {
            return false;
        }
    }
}
