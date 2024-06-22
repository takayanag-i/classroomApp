package jp.co.collasho.classroom.exception;


public class SignUpException extends Exception {


    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public SignUpException(String message) {
        super(message);
    }

    /**
     * メッセージおよび原因付きコンストラクタ
     * 
     * @param message
     * @param cause
     */
    public SignUpException(String message, Throwable cause) {
        super(message, cause);
    }
}
