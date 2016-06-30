package wn13.supercrm.model;

import java.io.Serializable;

/**
 * Created by wn13 on 2016/6/26.
 */
public class Department implements Serializable {
    private String departmentid;
    private String departmentname;
    private String parentid;
    private String departmentorder;
    private String departmentstatus;
    private String departmentremarks;

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public String getDepartmentorder() {
        return departmentorder;
    }

    public void setDepartmentorder(String departmentorder) {
        this.departmentorder = departmentorder;
    }

    public String getDepartmentstatus() {
        return departmentstatus;
    }

    public void setDepartmentstatus(String departmentstatus) {
        this.departmentstatus = departmentstatus;
    }

    public String getDepartmentremarks() {
        return departmentremarks;
    }

    public void setDepartmentremarks(String departmentremarks) {
        this.departmentremarks = departmentremarks;
    }
}
