package com.simplogics.hospitalManagement.advice;
import com.simplogics.hospitalManagement.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(HospitalException.class)
    public ResponseEntity<ResponseDTO> InvalidDataFormat(HospitalException msg) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setMessage(msg.getMessage());
        responseDTO.setHasError(true);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
    }
}
