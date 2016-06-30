package wn13.supercrm.model;

import java.io.Serializable;

/**
 * Created by wn13 on 2016/6/20.
 */
public class Staff implements Serializable{
    private int staffid;
    /**
     * 与微信userid相对应的标识
     */
    private String userid;
    /**
     * 即微信的openid
     */
    private String openid;
    private String name;
    private int departmentid;
    /**
     * 默认为0,1为负责人
     */
    private int leaderflag;
    /**
     * 职位
     */
    private String position;
    /**
     * 在部门中的次序值。order值小的排序靠前。普通员工为9999
     */
    private int order;
    private String mobile;
    private String tel;
    private String gender;
    private String email;
    private String weixinid;
    private String avatar;
    /**
     * 其他属性
     */
    private Object extattr;
    /**
     * -1未同步 0已同步到微信
     */
    private int staffstatus;
    /**
     * 默认1表示启用成员，0表示禁用成员
     */
    private int enable;
    private String staffremarks;
    private String departmentname;
    /**
     * 上级部门
     */
    private int parentid;
    /**
     * 在父部门中的次序值。order值小的排序靠前
     */
    private int departmentorder;
    /**
     * 1未同步 0已同步到微信
     */
    private int departmentstatus;
    private String departmentremarks;

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public int getParentid() {
        return parentid;
    }

    public void setParentid(int parentid) {
        this.parentid = parentid;
    }

    public int getDepartmentorder() {
        return departmentorder;
    }

    public void setDepartmentorder(int departmentorder) {
        this.departmentorder = departmentorder;
    }

    public int getDepartmentstatus() {
        return departmentstatus;
    }

    public void setDepartmentstatus(int departmentstatus) {
        this.departmentstatus = departmentstatus;
    }

    public String getDepartmentremarks() {
        return departmentremarks;
    }

    public void setDepartmentremarks(String departmentremarks) {
        this.departmentremarks = departmentremarks;
    }
}
