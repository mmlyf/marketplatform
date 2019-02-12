package com.mtpt.bean;

import java.util.Date;

public class MoReceive {
    private Integer id;

    private String phoneNum;

    private Date adTime;

    private String messageContent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public Date getAdTime() {
        return adTime;
    }

    public void setAdTime(Date adTime) {
        this.adTime = adTime;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent == null ? null : messageContent.trim();
    }
}