package jp.co.collasho.classroom.exception;


public class SignUpError extends Exception {


    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public SignUpError(String message) {
        super(message);
    }
}
