package com.mtpt.alibean;

public class TBSceneDataKey {
    private String labelId;

    private String sceneDn;

    public String getLabelId() {
        return labelId;
    }

    public void setLabelId(String labelId) {
        this.labelId = labelId == null ? null : labelId.trim();
    }

    public String getSceneDn() {
        return sceneDn;
    }

    public void setSceneDn(String sceneDn) {
        this.sceneDn = sceneDn == null ? null : sceneDn.trim();
    }
}