package edu.java.api;

import api.errorResponse.ApiErrorResponse;
import api.exception.AlreadyRegisteredException;
import api.exception.LinkAlreadyAddedException;
import api.exception.NotFoundException;
import api.exception.UncorrectedParametersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ApiErrorResponse> AlreadyRegisteredException(AlreadyRegisteredException exception) {
        return ResponseEntity
            .status(HttpStatus.ALREADY_REPORTED)
            .body(new ApiErrorResponse(
                "User already registered",
                "208",
                "AlreadyRegisteredException",
                "User already registered",
                exception.getStackTrace()
            ));
    }
    @ExceptionHandler(LinkAlreadyAddedException.class)
    public ResponseEntity<ApiErrorResponse> linkAlreadyAddedException(LinkAlreadyAddedException exception) {
        return ResponseEntity
            .status(HttpStatus.ALREADY_REPORTED)
            .body(new ApiErrorResponse(
                "Link already in array",
                "208",
                "LinkAlreadyAddedException",
                "This link is already in array",
                exception.getStackTrace()
            ));
    }
    @ExceptionHandler(UncorrectedParametersException.class)
    public ResponseEntity<ApiErrorResponse> uncorrectedParameters(UncorrectedParametersException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ApiErrorResponse(
               "Some of the parameters are not correct",
               "400",
               "UncorrectedParametersException",
                "Some of the parameters are not correct",
                exception.getStackTrace()
            ));
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(NotFoundException exception) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(new ApiErrorResponse(
                "Chat not found",
                "404",
                "NotFoundException",
                "Chat not found",
                exception.getStackTrace()
            ));
    }

}
