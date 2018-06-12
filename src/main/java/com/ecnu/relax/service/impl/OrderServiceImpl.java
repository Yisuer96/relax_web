package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.OrderBean;
import com.ecnu.relax.mapper.CommentMapper;
import com.ecnu.relax.mapper.OrderMapper;
import com.ecnu.relax.mapper.PreorderStatusMapper;
import com.ecnu.relax.mapper.SpecialistTypeMapper;
import com.ecnu.relax.model.Comment;
import com.ecnu.relax.model.Order;
import com.ecnu.relax.model.PreorderStatus;
import com.ecnu.relax.service.IOrderService;
import com.ecnu.relax.service.ISpecialistService;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    PreorderStatusMapper preorderStatusMapper;

    @Override
    public Integer cancelOrderByOrderId(Integer orderId) {
        Integer result = orderMapper.changeStatusByPrimaryKey(orderId, 2);
        int changeStatus = changePreOrderStatusBySpecialistId(getOrderDetailByOrderId(orderId).getSpecialistId(),
                new Date(getOrderDetailByOrderId(orderId).getConsultingStartTime()),
                new Date(getOrderDetailByOrderId(orderId).getConsultingFinishTime()),0,1);
        return result * changeStatus;
    }

    @Override
    public Integer continueOrderByOrderId(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        switch (order.getOrderStatus()) {
            case 1:
                return orderMapper.changeStatusByPrimaryKey(orderId, 3);
            case 3:
                return orderMapper.changeStatusByPrimaryKey(orderId, 4);
            case 4:
                return orderMapper.changeStatusByPrimaryKey(orderId, 7);
            case 7:
                return orderMapper.changeStatusByPrimaryKey(orderId, 8);
            case 8:
                order.setOrderStatus(9);
                order.setCompleteTime(new Date());
                return orderMapper.updateByPrimaryKeySelective(order);
            default:
                return 0;
        }
    }

    @Override
    public OrderBean getOrderDetailByOrderId(Integer orderId) {
        Order order = orderMapper.selectByPrimaryKey(orderId);
        String pN = getCurrentUser(order.getPatientId()).getRealName();
        String sN = getCurrentUser(order.getSpecialistId()).getRealName();
        String sP = getCurrentUser(order.getSpecialistId()).getPhone();
        OrderBean result = new OrderBean(order, pN, sN, sP);
        return result;
    }

    @Override
    public List<OrderBean> getOrdersByUser(Integer userId, Integer startNo, Integer size) {

        List<OrderBean> resultOrders = new ArrayList<>();
        List<Order> result = new ArrayList<>();
        result = orderMapper.getByNumber(userId, new RowBounds(startNo, size));

        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null) {
                OrderBean temp = getOrderDetailByOrderId(result.get(i).getOrderId());
                temp.setPartnerName(temp.getSpecialistName());
                resultOrders.add(temp);
            }
        }
        return resultOrders;
    }

    @Override
    public List<OrderBean> getOrdersBySpecialist(Integer specialistId, Integer startNo, Integer size) {
        List<OrderBean> resultOrders = new ArrayList<>();
        List<Order> result = new ArrayList<>();
        result = orderMapper.getByNumber1(specialistId, new RowBounds(startNo, size));
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i) != null) {
                OrderBean temp = getOrderDetailByOrderId(result.get(i).getOrderId());
                temp.setPartnerName(temp.getPatientName());
                resultOrders.add(temp);
            }
        }
        return resultOrders;
    }

    @Override
    public Integer submitCommentByOrder(Integer orderId, Double rating, String comment) {
        Comment record = new Comment(orderId, comment, rating);
        return commentMapper.insertSelective(record) *
                orderMapper.changeStatusByPrimaryKey(orderId, 9);
    }

    @Override
    public Integer placeNewOrder(int userId, int specialistId, int typeId, double sum, String description, Date start, Date end) {
        Order order = new Order();
        order.setPatientId(userId);
        order.setSpecialistId(specialistId);
        order.setTypeId(typeId);
        order.setOrderStatus(1);
        order.setConsultingWay(0);
        order.setPublishTime(new Date());
        order.setCompleteTime(new Date());
        order.setSum(sum);
        order.setDescription(description);
        order.setConsultingStartTime(start);
        order.setConsultingFinishTime(end);
        int changeStatus = changePreOrderStatusBySpecialistId(specialistId,
                start, end,1,1);
        return orderMapper.insertSelective(order) * changeStatus;
    }

    @Override
    public int changePreOrderStatusBySpecialistId(int specialistId, Date startTime, Date endTime, Integer isOrdered, Integer isFree) {
        Calendar start = Calendar.getInstance();
        start.setTime(startTime);
        Calendar end = Calendar.getInstance();
        end.setTime(endTime);
        List<PreorderStatus> records = new ArrayList<>();
        PreorderStatus preorderStatus;
        int temp = start.DAY_OF_WEEK - 1;
        if (temp == 0)
            temp = 7;
        switch (start.HOUR_OF_DAY) {
            case 10:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(1);
                records.add(preorderStatus);
                break;
            case 16:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(5);
                records.add(preorderStatus);
                break;
            case 19:
                if (end.HOUR_OF_DAY == 20) {
                    preorderStatus = new PreorderStatus();
                    preorderStatus.setTimeslotId(6);
                    records.add(preorderStatus);
                    break;
                } else {
                    for (int i = 0; i < 2; i++) {
                        preorderStatus = new PreorderStatus();
                        preorderStatus.setTimeslotId(6 + i);
                        records.add(preorderStatus);
                    }
                    break;
                }
            case 20:
                preorderStatus = new PreorderStatus();
                preorderStatus.setTimeslotId(7);
                records.add(preorderStatus);
                break;
            default:
                for (int i = 0; i < end.HOUR_OF_DAY - start.HOUR_OF_DAY; i++) {
                    preorderStatus = new PreorderStatus();
                    preorderStatus.setTimeslotId(start.HOUR_OF_DAY-11 + i);
                    records.add(preorderStatus);
                }
        }
        for(int i = 0;i<records.size();i++)
        {
            preorderStatus = records.get(i);
            preorderStatus.setDay(temp);
            preorderStatus.setSpecialistId(specialistId);
            preorderStatus.setIsOrdered(isOrdered);
            preorderStatus.setIsFree(isFree);
            preorderStatusMapper.updateByPrimaryKey(preorderStatus);
        }
        return 1;
    }
}
