package kr.or.yl.reservationservice.main.exception;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kr.or.yl.reservationservice.main.dto.ErrorResponse;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
	private static final HttpStatus INTERNAL_SERVER_ERROR = HttpStatus.INTERNAL_SERVER_ERROR;
	
	@ExceptionHandler(EmptyResultDataAccessException.class)
	public static ResponseEntity<ErrorResponse> invalidInputError(EmptyResultDataAccessException exception) {
		ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.value(), exception.getMessage());

		LOGGER.error("inExpect result Error", exception);
		return ResponseEntity
					.status(INTERNAL_SERVER_ERROR)
					.body(errorResponse);
	}
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<ErrorResponse> sqlExceptionError(SQLException exception) {
		ErrorResponse errorResponse = new ErrorResponse(INTERNAL_SERVER_ERROR.value(), exception.getMessage());
		
		LOGGER.error("SQL Exception Error", exception);
		return ResponseEntity
					.status(INTERNAL_SERVER_ERROR)
					.body(errorResponse);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionError(Exception exception) {
		ErrorResponse errorResponse = new ErrorResponse(BAD_REQUEST.value(), exception.getMessage());
		
		LOGGER.error("Exception Error", exception);
		return ResponseEntity
				.status(BAD_REQUEST)
				.body(errorResponse);
	}
	
}	
