package dao.exception;

public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }
    public DaoException(String message, Throwable cause){
        super(message,cause);
    }
}
