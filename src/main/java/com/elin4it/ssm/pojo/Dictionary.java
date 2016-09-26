package com.elin4it.ssm.pojo;

public class Dictionary {
    private Integer dictId;

    private String dictType;

    private String dictName;

    private String dictValue;

    private String dictFather;

    private Integer isFixed;

    public Integer getDictId() {
        return dictId;
    }

    public void setDictId(Integer dictId) {
        this.dictId = dictId;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    public Integer getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(Integer isFixed) {
        this.isFixed = isFixed;
    }

    public String getDictFather() {
        return dictFather;
    }

    public void setDictFather(String dictFather) {
        this.dictFather = dictFather;
    }
}