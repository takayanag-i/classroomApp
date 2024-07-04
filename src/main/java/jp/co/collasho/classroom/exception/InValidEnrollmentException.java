package jp.co.collasho.classroom.exception;

/**
 * 不正な履修登録があったときにスローされる例外
 */
public class InValidEnrollmentException extends Exception {
    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public InValidEnrollmentException(String message) {
        super(message);
    }
}
