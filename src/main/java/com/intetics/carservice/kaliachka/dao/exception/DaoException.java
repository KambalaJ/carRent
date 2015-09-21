package com.intetics.carservice.kaliachka.dao.exception;

/**
 * Created by AndreiK-PC on 20.09.2015.
 */
public class DaoException extends Exception {
    public DaoException(String message) {
        super(message);
    }

    public DaoException() {
        super();
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
