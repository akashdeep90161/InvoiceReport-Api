package com.walkover.user.api.handler;

import com.walkover.user.api.exception.*;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.utils.commons.MessageUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.beans.PropertyEditorSupport;
import java.util.Set;

import static com.walkover.user.api.models.commens.ApiStatus.error;


/**
 * @author Akash Deep Gupta
 **/


@ControllerAdvice
public class ApiExceptionHandler {

    @Autowired
    private MessageUtils messageUtils;

    private static final Logger LOGGER = LogManager.getLogger(ApiExceptionHandler.class);

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(text.toLowerCase());
            }
        });
    }


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<ApiResponse> alreadyExist(AlreadyExistException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ApiResponse> invalidRequest(InvalidRequestException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(InvalidParameterException.class)
    public ResponseEntity<ApiResponse> invalidParameter(InvalidParameterException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidHeaderException.class)
    public ResponseEntity<ApiResponse> invalidHeader(InvalidHeaderException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse> notFound(NotFoundException e) {
        return new ResponseEntity<>(new ApiResponse(e.getMessage(), error), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse> requestMethodNotSupported(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(new ApiResponse(messageUtils.t("error.request.methodNotSupported"), error),
                HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse> httpMessageNotReadable(HttpMessageNotReadableException e) {
        return new ResponseEntity<>(new ApiResponse(messageUtils.t("error.request.invalidJson"), error),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFound(NoHandlerFoundException ex, HttpServletRequest req) {
        return new ResponseEntity<>(new ApiResponse(String.format(messageUtils.t("error.request.resourceNotFound"),
                req.getRequestURI()), error), HttpStatus.NOT_FOUND);
    }

 /*   @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> unHandledExceptions(Exception e) {
        LOGGER.error("Exception: ", e);
        return new ResponseEntity<>(new ApiResponse(messageUtils.t("error.server.unhandled"), error),
                HttpStatus.INTERNAL_SERVER_ERROR);
    } */

  /*  @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiResponse> constraintViolation(ConstraintViolationException e) {
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        String violationMessage = "Invalid parameters provided for this request. ";
        if (constraintViolations.size() > 0) {
            violationMessage = "Following constraint(s) failed: ";
            violationMessage = constraintViolations.stream()
                    .map((constraintViolation) -> constraintViolation.getMessage() + " ")
                    .reduce(violationMessage, String::concat);
        }
        return new ResponseEntity<>(new ApiResponse(violationMessage.trim(), error), HttpStatus.INTERNAL_SERVER_ERROR);
    } */
}
