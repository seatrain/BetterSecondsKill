package com.seatrain.bettersecondskill.web.exception;

import com.seatrain.bettersecondskill.commons.exception.AbstractCutomizedExcetpion;
import com.seatrain.bettersecondskill.commons.exception.BadRequestException;
import com.seatrain.bettersecondskill.web.http.ExceptionResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@ControllerAdvice
public class GlobalExceptionHandler {


  // 处理系统内置的Exception
  @ExceptionHandler(Throwable.class)
  public ExceptionResponse exeption(HttpServletResponse response, Throwable ex) {
    return handleException(response, HttpStatus.INTERNAL_SERVER_ERROR.value(), ex, Level.WARN);
  }

  @ExceptionHandler({HttpRequestMethodNotSupportedException.class, HttpMediaTypeException.class})
  public ExceptionResponse badRequest(HttpServletResponse response, ServletException ex) {
    return handleException(response, HttpStatus.BAD_REQUEST.value(), ex, Level.WARN);
  }

  // 处理自定义的Exception
  @ExceptionHandler(AbstractCutomizedExcetpion.class)
  public ExceptionResponse badRequest(HttpServletResponse response, AbstractCutomizedExcetpion ex) {
    return handleException(response, ex.getStatus(), ex);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ExceptionResponse handleMethodArguementNotValidException(HttpServletResponse response, MethodArgumentNotValidException ex) {
    Optional<ObjectError> firstError = ex.getBindingResult().getAllErrors().stream().findFirst();
    if (firstError.isPresent()) {
      final String firstErrorMessage = firstError.get().getDefaultMessage();
      return handleException(response, HttpStatus.BAD_REQUEST.value(), new BadRequestException(firstErrorMessage));
    }
    return handleException(response, HttpStatus.BAD_REQUEST.value(), ex);
  }

  @ExceptionHandler(ConstraintViolationException.class)
  public ExceptionResponse handleConstraintViolationException(HttpServletResponse response, ConstraintViolationException ex) {
    return handleException(response, HttpStatus.BAD_REQUEST.value(), new BadRequestException(ex.getMessage()));
  }


  private ExceptionResponse handleException(HttpServletResponse response, int status, Throwable ex) {
    return handleException(response, status, ex, Level.ERROR);
  }

  private ExceptionResponse handleException(HttpServletResponse response, int status, Throwable ex, Level logLevel) {
    String message = ex.getMessage();
    printLog(message, ex, logLevel);

    ExceptionResponse attributes = new ExceptionResponse();
    attributes.setException(ex.getClass().getName());
    attributes.setMessage(message);
    attributes.setTime(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    attributes.setStatus(status);

    response.setContentType(MediaType.APPLICATION_JSON_UTF8.toString());
    response.setStatus(status);
    return attributes;
  }

  /**
   * 打印日志，其中level flag为日志等级:
   *
   * @param message 异常信息
   * @param ex 异常
   * @param logLevel 异常等级
   */
  private void printLog(String message, Throwable ex, Level logLevel) {
    switch (logLevel) {
      case ERROR:
        log.error(message, ex);
        break;
      case WARN:
        log.warn(message, ex);
        break;
      case INFO:
        log.info(message, ex);
        break;
      case DEBUG:
        log.debug(message, ex);
        break;
      case TRACE:
        log.trace(message, ex);
        break;
    }

  }
}
