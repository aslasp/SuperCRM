package wn13.supercrm.model;

/**
 * Created by wn13 on 2016/6/20.
 */
public class Customer {
    private int contactsid;
    private int customerid;
    private String contactsname;
    private int contactsage;
    /**
     * 性别：1：男，2：女
     */
    private int contactsgender;
    private String contactsmobile;
    private String contactstelephone;
    private String contactsemail;
    private String contactsaddress;
    private String contactszipcode;
    private String contactsqq;
    private String contactswechat;
    private String contactswangwang;
    private String contactsdeptname;
    private String contactsposition;
    private String contactsremarks;
    private String customername;
    private String profile;
    /**
     * 客户类型:1:重要客户；2：一般客户；3：低价值客户
     */
    private int customertype;
    /**
     * 当前状态：1：初访；2：意向；3：报价；4：成交；5：暂时搁置
     */
    private int customerstatus;
    /**
     * 地区,region表的id字段
     */
    private int regionid;
    /**
     * 上级客户
     */
    private int parentcustomerid;
    /**
     * 客户来源
     */
    private String customersource;
    /**
     * 公司规模，默认为0
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

    public int getContactsid() {
        return contactsid;
    }

    public void setContactsid(int contactsid) {
        this.contactsid = contactsid;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public String getContactsname() {
        return contactsname;
    }

    public void setContactsname(String contactsname) {
        this.contactsname = contactsname;
    }

    public int getContactsage() {
        return contactsage;
    }

    public void setContactsage(int contactsage) {
        this.contactsage = contactsage;
    }

    public int getContactsgender() {
        return contactsgender;
    }

    public void setContactsgender(int contactsgender) {
        this.contactsgender = contactsgender;
    }

    public String getContactsmobile() {
        return contactsmobile;
    }

    public void setContactsmobile(String contactsmobile) {
        this.contactsmobile = contactsmobile;
    }

    public String getContactstelephone() {
        return contactstelephone;
    }

    public void setContactstelephone(String contactstelephone) {
        this.contactstelephone = contactstelephone;
    }

    public String getContactsemail() {
        return contactsemail;
    }

    public void setContactsemail(String contactsemail) {
        this.contactsemail = contactsemail;
    }

    public String getContactsaddress() {
        return contactsaddress;
    }

    public void setContactsaddress(String contactsaddress) {
        this.contactsaddress = contactsaddress;
    }

    public String getContactszipcode() {
        return contactszipcode;
    }

    public void setContactszipcode(String contactszipcode) {
        this.contactszipcode = contactszipcode;
    }

    public String getContactsqq() {
        return contactsqq;
    }

    public void setContactsqq(String contactsqq) {
        this.contactsqq = contactsqq;
    }

    public String getContactswechat() {
        return contactswechat;
    }

    public void setContactswechat(String contactswechat) {
        this.contactswechat = contactswechat;
    }

    public String getContactswangwang() {
        return contactswangwang;
    }

    public void setContactswangwang(String contactswangwang) {
        this.contactswangwang = contactswangwang;
    }

    public String getContactsdeptname() {
        return contactsdeptname;
    }

    public void setContactsdeptname(String contactsdeptname) {
        this.contactsdeptname = contactsdeptname;
    }

    public String getContactsposition() {
        return contactsposition;
    }

    public void setContactsposition(String contactsposition) {
        this.contactsposition = contactsposition;
    }

    public String getContactsremarks() {
        return contactsremarks;
    }

    public void setContactsremarks(String contactsremarks) {
        this.contactsremarks = contactsremarks;
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
}
