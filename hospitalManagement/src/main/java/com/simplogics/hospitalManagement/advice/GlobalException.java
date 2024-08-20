package com.simplogics.hospitalManagement.advice;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(InvalidDataFormatException.class)
    public ResponseEntity<ResponseDTO> InvalidDataFormat(InvalidDataFormatException msg){
            ResponseDTO responseDTO=new ResponseDTO();
            responseDTO.setMessage(msg.getMessage());
            responseDTO.setHasError(true);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
        }
    
    @ExceptionHandler(NullRequestException.class)
    public ResponseEntity<ResponseDTO> NullRequestException(NullRequestException msg){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage(msg.getMessage());
        responseDTO.setHasError(true);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(FieldRequiredException.class)
    public ResponseEntity<ResponseDTO> FieldRequiredException(FieldRequiredException msg){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage(msg.getMessage());
        responseDTO.setHasError(true);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(NoDependencyException.class)
    public ResponseEntity<ResponseDTO> NoDependency(NoDependencyException msg){
        ResponseDTO responseDTO=new ResponseDTO();
        responseDTO.setMessage(msg.getMessage());
        responseDTO.setHasError(true);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
