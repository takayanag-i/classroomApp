package jp.co.collasho.classroom.exception;

/**
 * 不正な入力があったときにスローされる例外
 */
public class InvalidInputException extends Exception {
    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * メッセージおよび原因付きコンストラクタ
     * 
     * @param message
     * @param cause
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}
