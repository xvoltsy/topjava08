package ru.javawebinar.topjava.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * User: gkislin
 * Date: 19.08.2014
 */
@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "No data found")
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
