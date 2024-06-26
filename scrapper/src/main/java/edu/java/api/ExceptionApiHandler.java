package edu.java.api;

import api.errorResponse.ApiErrorResponse;
import api.exception.AlreadyRegisteredException;
import api.exception.DataBaseNoConnectedException;
import api.exception.LinkAlreadyAddedException;
import api.exception.NotFoundException;
import api.exception.UncorrectedParametersException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionApiHandler {
    private final String alreadyReported = "208";

    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<ApiErrorResponse> alreadyRegisteredException(AlreadyRegisteredException exception) {
        return ResponseEntity
            .status(HttpStatus.ALREADY_REPORTED)
            .body(new ApiErrorResponse(
                "User trying several times to register",
                alreadyReported,
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
                alreadyReported,
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
                "Parameters are not correct",
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
                "Chat not found in db",
                "404",
                "NotFoundException",
                "Chat not found",
                exception.getStackTrace()
            ));
    }

    @ExceptionHandler(DataBaseNoConnectedException.class)
    public ResponseEntity<ApiErrorResponse> notFoundException(DataBaseNoConnectedException exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ApiErrorResponse(
                "Database not working",
                "500",
                "DataBaseNoConnectedException",
                "Database error",
                exception.getStackTrace()
            ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> badException(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_GATEWAY)
            .body(new ApiErrorResponse(
                "Server error",
                "502",
                "BadException",
                "Unknown error",
                exception.getStackTrace()
            ));
    }
}
