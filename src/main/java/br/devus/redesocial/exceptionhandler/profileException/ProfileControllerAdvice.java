package br.devus.redesocial.exceptionhandler.profileException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.devus.redesocial.exceptionhandler.MessageExceptionHandler;

@ControllerAdvice(basePackages = "br.devus.redesocial.controller")
public class ProfileControllerAdvice {
    
    @ResponseBody
    @ExceptionHandler(ProfileAlreadyExistsException.class)
    public ResponseEntity<MessageExceptionHandler> profileAlreadyExists(ProfileAlreadyExistsException profileAlreadyExistsException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "Profile already exists");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> userNotFound(ProfileNotFoundException profileNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Profile not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
