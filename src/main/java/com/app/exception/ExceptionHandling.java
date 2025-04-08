package com.app.exception;

import com.app.payload.Athintication.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ExceptionHandling {

    // Handle UserExist Exception (when username already exists)
    @ExceptionHandler(UserExist.class)
    public ResponseEntity<Errordto> handleUserExistException(UserExist u, WebRequest req) {
        // Prepare error response
        Errordto dto = new Errordto();
        dto.setLocalDateTime(LocalDateTime.now());
        dto.setMessage(u.getMessage());
        dto.setRequest(req.getDescription(false));

        // Respond with Bad Request (400) because this is a user error
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    // Handle EmailExist Exception (when email already exists)
    @ExceptionHandler(EmailExist.class)
    public ResponseEntity<Errordto> handleEmailExistException(EmailExist e, WebRequest req) {
        // Prepare error response
        Errordto dto = new Errordto();
        dto.setLocalDateTime(LocalDateTime.now());
        dto.setMessage(e.getMessage());
        dto.setRequest(req.getDescription(false));

        // Respond with Bad Request (400) because this is a user error
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<Errordto> ResourceNotFoundException(ResourceNotFound r,WebRequest req){
        // Prepare error response
        Errordto dto = new Errordto();
        dto.setLocalDateTime(LocalDateTime.now());
        dto.setMessage(r.getMessage());
        dto.setRequest(req.getDescription(false));

        // Respond with Not Found (404) because the requested resource was not found
        return new ResponseEntity<>(dto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest req){
        Errordto dto = new Errordto();

        dto.setLocalDateTime(LocalDateTime.now());
        dto.setMessage(ex.getMessage());
        dto.setRequest(req.getDescription(false));

        // Respond with Internal Server Error (500) because something went wrong on the server side
        return new ResponseEntity<>(dto, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
