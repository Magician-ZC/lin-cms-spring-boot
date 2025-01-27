package io.github.talelin.latticy.module.message;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zly on 2017/6/12.
 */

public class Request<T> {
    private String action;


    private long seqId;

    private T req;

    private String deviceId;

    private String deviceTag;

    private transient int reqCount;

    public Request() {
    }


    public Request(String action, int reqEvent, long seqId, T req, int reqCount) {
        this.action = action;
//        this.reqEvent = reqEvent;
        this.seqId = seqId;
        this.req = req;
        this.reqCount = reqCount;

    }



    public String getAction() {
        return action;
    }


    public void setAction(String action) {
        this.action = action;
    }

//
//    public int getReqEvent() {
//        return reqEvent;
//    }
//
//
//    public void setReqEvent(int reqEvent) {
//        this.reqEvent = reqEvent;
//    }


    public long getSeqId() {
        return seqId;
    }


    public void setSeqId(long seqId) {
        this.seqId = seqId;
    }


    public T getReq() {
        return req;
    }


    public void setReq(T req) {
        this.req = req;
    }


    public int getReqCount() {
        return reqCount;
    }


    public void setReqCount(int reqCount) {
        this.reqCount = reqCount;
    }


    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceTag() {
        return deviceTag;
    }

    public void setDeviceTag(String deviceTag) {
        this.deviceTag = deviceTag;
    }

    public static class Builder<T> {
        private String action;
        private int reqEvent;
        private long seqId;
        private T req;
        private int reqCount;

        public Builder action(String action) {
            this.action = action;
            return this;
        }


        public Builder reqEvent(int reqEvent) {
            this.reqEvent = reqEvent;
            return this;
        }


        public Builder seqId(long seqId) {
            this.seqId = seqId;
            return this;
        }


        public Builder req(T req) {
            this.req = req;
            return this;
        }


        public Builder reqCount(int reqCount) {
            this.reqCount = reqCount;
            return this;
        }

        public Request build() {
            return new Request<T>(action, reqEvent, seqId, req, reqCount);
        }

    }
}
