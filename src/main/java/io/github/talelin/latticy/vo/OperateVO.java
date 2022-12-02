package io.github.talelin.latticy.vo;


import io.github.talelin.autoconfigure.bean.Code;
import io.github.talelin.latticy.common.util.ResponseUtil;
import org.springframework.http.HttpStatus;

/**
 * 操作成功视图对象
 */
public class OperateVO extends UnifyResponseVO<String>{
    public OperateVO() {
        super(Code.SUCCESS.getCode());
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public OperateVO(int code) {
        super(code);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public OperateVO(String message) {
        super(message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    public OperateVO(int code, String message) {
        super(code, message);
        ResponseUtil.setCurrentResponseHttpStatus(HttpStatus.OK.value());
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
