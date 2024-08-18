package net.sayilir.api.common;

import jakarta.servlet.http.HttpServletRequest;
import net.sayilir.api.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.xml.bind.ValidationException;
import java.util.Collections;
import java.util.List;
/**
 * @author omersayilir
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(HttpServletRequest request, Exception ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        ApiResponse<Void> response = ResponseUtil.error(errors, 1000, "Internal Server Error", request.getRequestURI());
        LOG.error("Error: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(HttpServletRequest request, Exception ex) {
        ApiResponse<Void> response=ResponseUtil.error(ex.getMessage(), 1002, "Resource Not Found", request.getRequestURI());
        LOG.error("Error: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidateException(HttpServletRequest request, ValidationException ex) {
        ApiResponse<Void> response=ResponseUtil.error(ex.getMessage(), 1003, "Validation failed", request.getRequestURI());
        LOG.error("Error: {}", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
