package com.mtpt.bean;

import java.util.Date;
import java.util.List;

public class TBUsers {
    private String id;

    private String nickname;

    private String name;

    private String mobile;

    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    private String unionid;

    private Date addtime;

    private Date modifytime;

    private Integer datastate;

    private String networktype;

    private String registationlocation;

    private String openid;

    private Short usertype;
    
    private List<FTPDetails> ftpDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country == null ? null : country.trim();
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl == null ? null : headimgurl.trim();
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid == null ? null : unionid.trim();
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

    public String getNetworktype() {
        return networktype;
    }

    public void setNetworktype(String networktype) {
        this.networktype = networktype == null ? null : networktype.trim();
    }

    public String getRegistationlocation() {
        return registationlocation;
    }

    public void setRegistationlocation(String registationlocation) {
        this.registationlocation = registationlocation == null ? null : registationlocation.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Short getUsertype() {
        return usertype;
    }

    public void setUsertype(Short usertype) {
        this.usertype = usertype;
    }

	public List<FTPDetails> getFtpDetails() {
		return ftpDetails;
	}

	public void setFtpDetails(List<FTPDetails> ftpDetails) {
		this.ftpDetails = ftpDetails;
	}
    
    
}