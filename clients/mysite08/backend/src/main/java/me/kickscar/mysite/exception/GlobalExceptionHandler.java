package me.kickscar.mysite.exception;

import lombok.extern.slf4j.Slf4j;
import me.kickscar.mysite.dto.JsonResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
	
	private static final Log LOGGER = LogFactory.getLog(GlobalExceptionHandler.class);
	
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseEntity<Object> handlerException(Exception e) {
		// 1. 로깅(logging)
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		LOGGER.error(errors.toString());
		
		// 2. JSON 응답
		return ResponseEntity.status(HttpStatus.OK).body(JsonResult.fail(errors.toString()));
	}
}