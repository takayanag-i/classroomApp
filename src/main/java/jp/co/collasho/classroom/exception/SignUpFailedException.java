package jp.co.collasho.classroom.exception;

/**
 * 新規学生登録に失敗したときにスローされる例外
 */
public class SignUpFailedException extends Exception {
    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public SignUpFailedException(String message) {
        super(message);
    }
}
