package com.volsync.volsyncproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception thrown when a resource that we are trying to get doesn't exist in the database
 *
 * Note: HttpStatus of NOT_FOUND will be attached to this exception
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message){
        super(message);

    }

}
