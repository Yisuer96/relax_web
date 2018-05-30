package com.ecnu.relax.model;

import java.util.Date;

public class Order {
    private Integer orderId;

    private Integer patientId;

    private Integer specialistId;

    private Integer typeId;

    private Integer orderStatus;

    private Integer consultingWay;

    private Date publishTime;

    private Date completeTime;

    private Double sum;

    private String location;

    private String description;

    private Date consultingStartTime;

    private Date consultingFinishTime;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    public Integer getSpecialistId() {
        return specialistId;
    }

    public void setSpecialistId(Integer specialistId) {
        this.specialistId = specialistId;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getConsultingWay() {
        return consultingWay;
    }

    public void setConsultingWay(Integer consultingWay) {
        this.consultingWay = consultingWay;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public Date getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getConsultingStartTime() {
        return consultingStartTime;
    }

    public void setConsultingStartTime(Date consultingStartTime) {
        this.consultingStartTime = consultingStartTime;
    }

    public Date getConsultingFinishTime() {
        return consultingFinishTime;
    }

    public void setConsultingFinishTime(Date consultingFinishTime) {
        this.consultingFinishTime = consultingFinishTime;
    }
}