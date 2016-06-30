package wn13.supercrm.model;

import java.io.Serializable;

/**
 * Created by wn13 on 2016/6/23.
 */
public class Customer implements Serializable {
    private int customerid;
    private String customername;
    /**
     * 简介
     */
    private String profile;
    /**
     * 客户类型:1:重要客户；2：一般客户；3：低价值客户
     */
    private int customertype;
    /**
     * 当前状态：1：初访；2：意向；3：报价；4：成交；5：暂时搁置
     */
    private int customerstatus;
    private int regionid;
    private int parentcustomerid;
    private String customersource;
    /**
     * 公司规模
     */
    private int size;
    private String telephone;
    private String email;
    private String website;
    private String address;
    private String zipcode;
    private int staffid;
    private String createdate;
    private String customerremarks;
    private String userid;
    private String openid;
    private String name;
    private int departmentid;
    private int leaderflag;
    private String position;
    private int order;
    private String mobile;
    private String tel;
    private String gender;
    private String weixinid;
    private String avatar;
    private Object extattr;
    private int staffstatus;
    private int enable;
    private String staffremarks;

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public int getCustomertype() {
        return customertype;
    }

    public void setCustomertype(int customertype) {
        this.customertype = customertype;
    }

    public int getCustomerstatus() {
        return customerstatus;
    }

    public void setCustomerstatus(int customerstatus) {
        this.customerstatus = customerstatus;
    }

    public int getRegionid() {
        return regionid;
    }

    public void setRegionid(int regionid) {
        this.regionid = regionid;
    }

    public int getParentcustomerid() {
        return parentcustomerid;
    }

    public void setParentcustomerid(int parentcustomerid) {
        this.parentcustomerid = parentcustomerid;
    }

    public String getCustomersource() {
        return customersource;
    }

    public void setCustomersource(String customersource) {
        this.customersource = customersource;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getCustomerremarks() {
        return customerremarks;
    }

    public void setCustomerremarks(String customerremarks) {
        this.customerremarks = customerremarks;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(int departmentid) {
        this.departmentid = departmentid;
    }

    public int getLeaderflag() {
        return leaderflag;
    }

    public void setLeaderflag(int leaderflag) {
        this.leaderflag = leaderflag;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWeixinid() {
        return weixinid;
    }

    public void setWeixinid(String weixinid) {
        this.weixinid = weixinid;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Object getExtattr() {
        return extattr;
    }

    public void setExtattr(Object extattr) {
        this.extattr = extattr;
    }

    public int getStaffstatus() {
        return staffstatus;
    }

    public void setStaffstatus(int staffstatus) {
        this.staffstatus = staffstatus;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getStaffremarks() {
        return staffremarks;
    }

    public void setStaffremarks(String staffremarks) {
        this.staffremarks = staffremarks;
    }
}
