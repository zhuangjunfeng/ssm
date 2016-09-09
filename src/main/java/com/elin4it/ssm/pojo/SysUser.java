package com.elin4it.ssm.pojo;

public class SysUser {
    private Integer yhId;

    private String yhxm;

    private String password;

    private String yhxb;

    private String yhyx;

    private String yhzh;

    private String yhjs;

    private String cjsj;

    public Integer getYhId() {
        return yhId;
    }

    public void setYhId(Integer yhId) {
        this.yhId = yhId;
    }

    public String getYhxm() {
        return yhxm;
    }

    public void setYhxm(String yhxm) {
        this.yhxm = yhxm == null ? null : yhxm.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getYhxb() {
        return yhxb;
    }

    public void setYhxb(String yhxb) {
        this.yhxb = yhxb == null ? null : yhxb.trim();
    }

    public String getYhyx() {
        return yhyx;
    }

    public void setYhyx(String yhyx) {
        this.yhyx = yhyx == null ? null : yhyx.trim();
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh == null ? null : yhzh.trim();
    }

    public String getYhjs() {
        return yhjs;
    }

    public void setYhjs(String yhjs) {
        this.yhjs = yhjs == null ? null : yhjs.trim();
    }

    public String getCjsj() {
        return cjsj;
    }

    public void setCjsj(String cjsj) {
        this.cjsj = cjsj == null ? null : cjsj.trim();
    }
}