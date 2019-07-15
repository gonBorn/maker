package com.platform.maker.exception;


import com.platform.maker.Enum.ResultEnum;
import lombok.Getter;

/**
 * Created by 廖师兄
 * 2017-06-11 18:55
 */
@Getter
public class MakerException extends RuntimeException{

    private Integer code;

    public MakerException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public MakerException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
