package wn13.supercrm.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import java.io.Serializable;

import wn13.supercrm.BR;

/**
 * Created by wn13 on 2016/6/21.
 */
public class Product extends BaseObservable implements Serializable{
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

    @Bindable
    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
        notifyPropertyChanged(BR.productid);
    }

    @Bindable
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
        notifyPropertyChanged(BR.productname);
    }

    @Bindable
    public String getProductsn() {
        return productsn;
    }

    public void setProductsn(String productsn) {
        this.productsn = productsn;
        notifyPropertyChanged(BR.productsn);
    }

    @Bindable
    public double getStandardprice() {
        return standardprice;
    }

    public void setStandardprice(double standardprice) {
        this.standardprice = standardprice;
        notifyPropertyChanged(BR.standardprice);
    }

    @Bindable
    public String getSalesunit() {
        return salesunit;
    }

    public void setSalesunit(String salesunit) {
        this.salesunit = salesunit;
        notifyPropertyChanged(BR.salesunit);
    }

    @Bindable
    public double getUnitcost() {
        return unitcost;
    }

    public void setUnitcost(double unitcost) {
        this.unitcost = unitcost;
        notifyPropertyChanged(BR.unitcost);
    }

    @Bindable
    public int getClassification() {
        return classification;
    }

    public void setClassification(int classification) {
        this.classification = classification;
        notifyPropertyChanged(BR.classification);
    }

    @Bindable
    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
        notifyPropertyChanged(BR.picture);
    }

    @Bindable
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
        notifyPropertyChanged(BR.introduction);
    }

    @Bindable
    public String getProductremarks() {
        return productremarks;
    }

    public void setProductremarks(String productremarks) {
        this.productremarks = productremarks;
        notifyPropertyChanged(BR.productremarks);
    }
}
