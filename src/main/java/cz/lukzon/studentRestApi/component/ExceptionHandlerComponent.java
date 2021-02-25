package cz.lukzon.studentRestApi.component;

import cz.lukzon.studentRestApi.dto.BasicInfoDTO;
import cz.lukzon.studentRestApi.exception.ConflictException;
import cz.lukzon.studentRestApi.exception.DataNotFoundException;
import cz.lukzon.studentRestApi.exception.ProccesingRejectedException;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerComponent {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerComponent.class);

    @ExceptionHandler(value = {ConflictException.class})
    public ResponseEntity<BasicInfoDTO> handleConflictException(ConflictException ex) {
        logger.error("Conflict exception: " + ex.getMessage());
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(ex.getMessage(), false, HttpStatus.CONFLICT.value());
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {DataNotFoundException.class})
    public ResponseEntity<BasicInfoDTO> handleDataNotFoundException(DataNotFoundException ex) {
        logger.error("Data not found exception: " + ex.getMessage());
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(ex.getMessage(), false, HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProccesingRejectedException.class})
    public ResponseEntity<BasicInfoDTO> handleProccesingRejectedException(ProccesingRejectedException ex) {
        logger.error("Proccesing rejected exception: " + ex.getMessage());
        BasicInfoDTO basicInfoDTO = new BasicInfoDTO(ex.getMessage(), false, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(basicInfoDTO, HttpStatus.BAD_REQUEST);
    }
}
