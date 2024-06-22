package jp.co.collasho.classroom.exception;

public class ValidationError extends Exception {
    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public ValidationError(String message) {
        super(message);
    }

    /**
     * メッセージおよび原因付きコンストラクタ
     * 
     * @param message
     * @param cause
     */
    public ValidationError(String message, Throwable cause) {
        super(message, cause);
    }
}
