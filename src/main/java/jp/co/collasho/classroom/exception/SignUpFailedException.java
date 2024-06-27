package jp.co.collasho.classroom.exception;


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
