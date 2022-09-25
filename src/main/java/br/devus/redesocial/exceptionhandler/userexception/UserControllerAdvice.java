package br.devus.redesocial.exceptionhandler.userexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.devus.redesocial.exceptionhandler.MessageExceptionHandler;

import java.util.Date;

@ControllerAdvice(basePackages = "br.devus.redesocial.controller")
public class UserControllerAdvice {

    @ResponseBody
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> userNotFound(UserNotFoundException userNotFound){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "User not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<MessageExceptionHandler> userAlreadyExists(UserAlreadyExistsException userAlreadyExistsException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), "User already exists!");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
