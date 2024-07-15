package com.simplogics.hospitalManagement.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ResponseDTO {
    private String message="Success";
    private Boolean hasError=false;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;
}
