package com.mtpt.bean;

import java.util.Date;

public class Products {
    private String id;

    private String entrancename;

    private String productcode23g;

    private String productcode4g;

    private String productname;

    private Float cost;

    private Integer amount;

    private Integer vagabondizetype;

    private Integer packagetype;

    private Integer effecttype;

    private String flag;

    private Boolean recommendation;

    private String province;

    private Date addtime;

    private Date modifytime;

    private Integer datastate;

    private Integer direction;

    private Boolean enabled;

    private Integer recommendationorder;

    private String amountdesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getEntrancename() {
        return entrancename;
    }

    public void setEntrancename(String entrancename) {
        this.entrancename = entrancename == null ? null : entrancename.trim();
    }

    public String getProductcode23g() {
        return productcode23g;
    }

    public void setProductcode23g(String productcode23g) {
        this.productcode23g = productcode23g == null ? null : productcode23g.trim();
    }

    public String getProductcode4g() {
        return productcode4g;
    }

    public void setProductcode4g(String productcode4g) {
        this.productcode4g = productcode4g == null ? null : productcode4g.trim();
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname == null ? null : productname.trim();
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getVagabondizetype() {
        return vagabondizetype;
    }

    public void setVagabondizetype(Integer vagabondizetype) {
        this.vagabondizetype = vagabondizetype;
    }

    public Integer getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(Integer packagetype) {
        this.packagetype = packagetype;
    }

    public Integer getEffecttype() {
        return effecttype;
    }

    public void setEffecttype(Integer effecttype) {
        this.effecttype = effecttype;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag == null ? null : flag.trim();
    }

    public Boolean getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(Boolean recommendation) {
        this.recommendation = recommendation;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public void setModifytime(Date modifytime) {
        this.modifytime = modifytime;
    }

    public Integer getDatastate() {
        return datastate;
    }

    public void setDatastate(Integer datastate) {
        this.datastate = datastate;
    }

    public Integer getDirection() {
        return direction;
    }

    public void setDirection(Integer direction) {
        this.direction = direction;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getRecommendationorder() {
        return recommendationorder;
    }

    public void setRecommendationorder(Integer recommendationorder) {
        this.recommendationorder = recommendationorder;
    }

    public String getAmountdesc() {
        return amountdesc;
    }

    public void setAmountdesc(String amountdesc) {
        this.amountdesc = amountdesc == null ? null : amountdesc.trim();
    }
}