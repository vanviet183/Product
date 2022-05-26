package com.example.products.Constant;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

public enum ResponseMessageEnum {
    SUCCESS("Response success"),//create enum to return result
    ERROR("Response error");

    private String message;

    ResponseMessageEnum(){}
     ResponseMessageEnum(String message){
        this.message = message;
    }

}
