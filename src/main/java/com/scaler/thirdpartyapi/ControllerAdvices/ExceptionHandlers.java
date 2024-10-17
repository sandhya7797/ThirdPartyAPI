package com.scaler.thirdpartyapi.ControllerAdvices;

import com.scaler.thirdpartyapi.Exceptions.ProductNotExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//A controller advice allows to catch all exceptions through out the application from different controllers. Learn more about it.
//Exception Handlers can be class level as well as global level. These will replace try-catch blocks in controllers.

@ControllerAdvice
public class ExceptionHandlers {

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

}
