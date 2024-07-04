package jp.co.collasho.classroom.exception;

/**
 * ログインに失敗したときにスローされる例外
 */
public class LoginFailedException extends Exception {
    /**
     * メッセージ付きコンストラクタ
     * 
     * @param message
     */
    public LoginFailedException(String message) {
        super(message);
    }
}
