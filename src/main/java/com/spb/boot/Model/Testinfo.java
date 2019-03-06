package com.spb.boot.Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Testinfo")
public class Testinfo extends EntityId{

    @Column(name = "testname",length = 255)
    private String testname;

    @Column(name = "tel",length = 255)
    private String tel;

    @Column(name = "address",length = 255)
    private String address;

    @Column(name = "remark",length = 255)
    private String remark;

    public String getTestname() {
        return testname;
    }

    public void setTestname(String testname) {
        this.testname = testname;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
