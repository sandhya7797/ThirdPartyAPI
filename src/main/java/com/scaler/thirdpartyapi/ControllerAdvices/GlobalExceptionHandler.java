package com.scaler.thirdpartyapi.ControllerAdvices;

import com.scaler.thirdpartyapi.Exceptions.CategoryNotExistsException;
import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//A ControllerAdvice allows to catch all exceptions through out the application from different controllers and using controllerAdvice methods we can modify the exception with custom info.
//Exception Handlers can be at class level as well as at global level. These will replace try-catch blocks in controllers.

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ExceptionDTO> handleArithmeticException() {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage("something went wrong");
        return new ResponseEntity<>(dto,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IndexOutOfBoundsException.class)
    public ResponseEntity<Void> handleIndexOutOfBoundsException() {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProductNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleProductNotExistsException(ProductNotExistsException ex) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(ex.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryNotExistsException.class)
    public ResponseEntity<ExceptionDTO> handleException(CategoryNotExistsException ex) {
        ExceptionDTO dto = new ExceptionDTO();
        dto.setMessage(ex.getMessage());
        return new ResponseEntity<>(dto,HttpStatus.NOT_FOUND);
    }

}
