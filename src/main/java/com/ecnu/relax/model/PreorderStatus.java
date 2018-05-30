package com.ecnu.relax.model;

public class PreorderStatus extends PreorderStatusKey {
    private Integer isOdered;

    private Integer isFree;

    private Integer day;

    public Integer getIsOdered() {
        return isOdered;
    }

    public void setIsOdered(Integer isOdered) {
        this.isOdered = isOdered;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }
}