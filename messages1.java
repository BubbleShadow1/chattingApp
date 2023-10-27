package com.androidapp.whatsappproject;

public class messages1 {
    public messages1() {
    }

    public messages1(String msgid, String senderid, String message) {
        this.msgid = msgid;
        this.senderid = senderid;
        this.message = message;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getSenderid() {
        return senderid;
    }

    public void setSenderid(String senderid) {
        this.senderid = senderid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String msgid;
    private String senderid;
    private String message;

}
