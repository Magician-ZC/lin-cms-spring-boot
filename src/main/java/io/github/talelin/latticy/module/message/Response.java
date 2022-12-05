package io.github.talelin.latticy.module.message;


import com.google.gson.annotations.SerializedName;
import io.github.talelin.latticy.common.util.TextUtil;

/**
 * Created by zly on 2017/2/9.
 */

public class Response {

    private String seqId;//一个唯一标识符，客户端用来处理消息是否成功

    private String action;//事件类型

    private String resp;//事件内容 json

    private String type;//1后端自动推送，2 后端响应客户端

//    public int getRespEvent() {
//        return respEvent;
//    }
//
//    public void setRespEvent(int respEvent) {
//        this.respEvent = respEvent;
//    }

    public String getSeqId() {
        return TextUtil.isEmpty(seqId) ? "" : seqId;
    }

    public void setSeqId(String seqId) {
        this.seqId = seqId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Response{" +
                "seqId='" + seqId + '\'' +
                ", action='" + action + '\'' +
                ", resp='" + resp + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
