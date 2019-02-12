package com.mtpt.bean;

public class TBMssage {
    private Integer misId;

    private String misTitle;

    private String misContent;

    public Integer getMisId() {
        return misId;
    }

    public void setMisId(Integer misId) {
        this.misId = misId;
    }

    public String getMisTitle() {
        return misTitle;
    }

    public void setMisTitle(String misTitle) {
        this.misTitle = misTitle == null ? null : misTitle.trim();
    }

    public String getMisContent() {
        return misContent;
    }

    public void setMisContent(String misContent) {
        this.misContent = misContent == null ? null : misContent.trim();
    }
}