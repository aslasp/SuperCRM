package wn13.supercrm.model;

/**
 * Created by wn13 on 2016/6/21.
 */
public class Product {
    private int productid;
    private String productname;
    /**
     * 产品序列号
     */
    private String productsn;
    /**
     * 标准单价
     */
    private double standardprice;
    /**
     * 销售单位
     */
    private String salesunit;
    /**
     * 单位成本
     */
    private double unitcost;
    /**
     * 产品分类
     */
    private int classification;
    private String picture;
    private String introduction;
    private String productremarks;

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductsn() {
        return productsn;
    }

    public void setProductsn(String productsn) {
        this.productsn = productsn;
    }

    public double getStandardprice() {
        return standardprice;
    }

    public void setStandardprice(double standardprice) {
        this.standardprice = standardprice;
    }

    public String getSalesunit() {
        return salesunit;
    }

    public void setSalesunit(String salesunit) {
        this.salesunit = salesunit;
    }

    public double getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(double unitcost) {
        this.unitcost = unitcost;
    }

    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getProductremarks() {
        return productremarks;
    }

    public void setProductremarks(String productremarks) {
        this.productremarks = productremarks;
    }
}
