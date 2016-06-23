package wn13.supercrm.model;

/**
 * Created by wn13 on 2016/6/21.
 */
public class Opportunity {
    private int opportunityid;
    private String opportunitytitle;
    private int customerid;
    /**
     * 预计销售金额
     */
    private double estimatedamount;
    /**
     * 成功率，百分比的分子
     */
    private int successrate;
    /**
     * 预计签单日期
     */
    private String expecteddate;
    /**
     * 状态：1:初步洽谈；2：需求确定；3：方案报价；4：谈判合同；5：赢单；6：输单
     */
    private int opportunitystatus;
    /**
     * 渠道商
     */
    private String channel;
    /**
     * 商机类型
     */
    private int businesstype;
    /**
     * 商机获取日期
     */
    private String acquisitiondate;
    /**
     * 商机来源
     */
    private String opportunitiessource;
    private int staffid;
    private String opportunityremarks;
    private String customername;
    /**
     * 客户简介
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
    private int size;
    private String telephone;
    private String email;
    private String website;
    private String address;
    private String zipcode;
    private String createdate;
    private String customerremarks;

    public int getOpportunityid() {
        return opportunityid;
    }

    public void setOpportunityid(int opportunityid) {
        this.opportunityid = opportunityid;
    }

    public String getOpportunitytitle() {
        return opportunitytitle;
    }

    public void setOpportunitytitle(String opportunitytitle) {
        this.opportunitytitle = opportunitytitle;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public double getEstimatedamount() {
        return estimatedamount;
    }

    public void setEstimatedamount(double estimatedamount) {
        this.estimatedamount = estimatedamount;
    }

    public int getSuccessrate() {
        return successrate;
    }

    public void setSuccessrate(int successrate) {
        this.successrate = successrate;
    }

    public String getExpecteddate() {
        return expecteddate;
    }

    public void setExpecteddate(String expecteddate) {
        this.expecteddate = expecteddate;
    }

    public int getOpportunitystatus() {
        return opportunitystatus;
    }

    public void setOpportunitystatus(int opportunitystatus) {
        this.opportunitystatus = opportunitystatus;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public int getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(int businesstype) {
        this.businesstype = businesstype;
    }

    public String getAcquisitiondate() {
        return acquisitiondate;
    }

    public void setAcquisitiondate(String acquisitiondate) {
        this.acquisitiondate = acquisitiondate;
    }

    public String getOpportunitiessource() {
        return opportunitiessource;
    }

    public void setOpportunitiessource(String opportunitiessource) {
        this.opportunitiessource = opportunitiessource;
    }

    public int getStaffid() {
        return staffid;
    }

    public void setStaffid(int staffid) {
        this.staffid = staffid;
    }

    public String getOpportunityremarks() {
        return opportunityremarks;
    }

    public void setOpportunityremarks(String opportunityremarks) {
        this.opportunityremarks = opportunityremarks;
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
