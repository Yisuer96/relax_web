package com.ecnu.relax.model;

public class PreorderStatus extends PreorderStatusKey {
    private Integer isOrdered;

    private Integer isFree;

    public Integer getIsOrdered() {
        return isOrdered;
    }

    public void setIsOrdered(Integer isOrdered) {
        this.isOrdered = isOrdered;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }


}