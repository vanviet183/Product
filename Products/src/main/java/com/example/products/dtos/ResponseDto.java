package com.example.products.dtos;

import com.example.products.Constant.ResponseMessageEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseDto<T> {
    private Integer status;
    private ResponseMessageEnum message;
    private T result;
}
