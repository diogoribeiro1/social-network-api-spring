package br.devus.redesocial.exceptionhandler.publicationExceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import br.devus.redesocial.exceptionhandler.MessageExceptionHandler;

@ControllerAdvice(basePackages = "br.devus.redesocial.controller")
public class PublicationControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PublicationNotFoundException.class)
    public ResponseEntity<MessageExceptionHandler> userNotFound(PublicationNotFoundException publicationNotFoundException){
        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.NOT_FOUND.value(), "Publication not found");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
    
}
