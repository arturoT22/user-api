package cl.ionix.user.controller;

import cl.ionix.user.controller.dto.base.BaseResponseDto;
import cl.ionix.user.controller.dto.base.ResultCodeType;
import cl.ionix.user.exception.UserFoundException;
import cl.ionix.user.exception.UserNotFoundException;
import cl.ionix.user.exception.UsersDatabaseIsEmptyException;
import cl.ionix.user.util.Constant;
import cl.ionix.user.util.MessageSourceUtilities;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResponseExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UsersDatabaseIsEmptyException.class})
    public final BaseResponseDto<Void> HandleUsersDatabaseIsEmptyException(UsersDatabaseIsEmptyException exception){
        System.out.println("handleUsersDatabaseException "+ exception);
        return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_USERS_DATABASE_IS_EMPTY));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class})
    public final BaseResponseDto<Void> HandleUserNotFoundException(UserNotFoundException exception){
        System.out.println("handleUserNotInWhiteListException "+ exception);
        return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_USER_NOT_EXISTS));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({UserFoundException.class})
    public final BaseResponseDto<Void> HandleUserFoundException(UserFoundException exception){
        System.out.println("handleUserNotInWhiteListException "+ exception);
        return new BaseResponseDto<>(ResultCodeType.ERROR, MessageSourceUtilities.getValue(Constant.MSGE_ERROR_USER_EXISTS));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final BaseResponseDto<Void> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new BaseResponseDto<>(ResultCodeType.ERROR, errors.toString());
    }
}
