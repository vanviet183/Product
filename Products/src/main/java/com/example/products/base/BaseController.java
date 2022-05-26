package com.example.products.base;

import com.example.products.Constant.ResponseMessageEnum;
import com.example.products.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public  class BaseController<T> {
    public ResponseEntity<?> resSuccess(T data){
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseDto<T>(HttpStatus.OK.value(),
                        ResponseMessageEnum.SUCCESS,data));
    }
    public ResponseEntity<?> resListSuccess(List<T> listData){
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ResponseDto<T>(HttpStatus.OK.value() ,
                        ResponseMessageEnum.SUCCESS
                        , (T) listData));
    }
    public ResponseEntity<?> resFailure(){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ResponseDto<>(HttpStatus.BAD_REQUEST.value(),
                        ResponseMessageEnum.ERROR , "Response error"));
    }
}
