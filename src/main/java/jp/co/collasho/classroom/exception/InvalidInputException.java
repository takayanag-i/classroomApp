package jp.co.collasho.classroom.exception;

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
