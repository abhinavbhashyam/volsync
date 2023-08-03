package com.volsync.volsyncproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when users with duplicate names are trying to be inserted into the database
 *
 * Note: HttpStatus of CONFLICT will be attached to this exception
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException(String message){
        super(message);

    }
}
