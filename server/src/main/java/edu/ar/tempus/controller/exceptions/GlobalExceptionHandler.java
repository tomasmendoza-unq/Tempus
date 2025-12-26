package edu.ar.tempus.controller.exceptions;

import edu.ar.tempus.exceptions.business.BusinessException;
import edu.ar.tempus.exceptions.business.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDTO> handleException(
            BusinessException ex,
            HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.of(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Error de validación de negocio",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleNotFoundException(
            EntityNotFoundException ex,
            HttpServletRequest request){
        ErrorResponseDTO error = ErrorResponseDTO.of(
                HttpStatus.NOT_FOUND.value(),
                "Recurso no encontrado",
                ex.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

}
