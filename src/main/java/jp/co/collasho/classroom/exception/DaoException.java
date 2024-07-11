package jp.co.collasho.classroom.exception;

import java.sql.SQLException;

public class DaoException extends Exception {

    /** エラーコード */
    private int errorCode;

    /**
     * 原因付きコンストラクタ
     * 
     * @param message
     * @param cause
     */
    public DaoException(SQLException cause) {
        super(cause);
        this.errorCode = cause.getErrorCode();
    }

    public int getErrorCode() {
        return this.errorCode;
    }
}
