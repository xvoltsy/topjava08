package ru.javawebinar.topjava.util.exception;


import javax.validation.constraints.NotNull;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
public class NotFoundException extends RuntimeException {
    public NotFoundException(@NotNull String message) {
        super(message);
    }
}
