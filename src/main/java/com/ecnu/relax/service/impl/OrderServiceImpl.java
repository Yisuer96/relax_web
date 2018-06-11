package com.ecnu.relax.service.impl;

import com.ecnu.relax.dto.OrderBean;
import com.ecnu.relax.mapper.CommentMapper;
import com.ecnu.relax.mapper.OrderMapper;
import com.ecnu.relax.model.Comment;
import com.ecnu.relax.model.Order;
import com.ecnu.relax.service.IOrderService;
import org.apache.ibatis.session.RowBounds;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl extends BaseServiceImpl implements IOrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public Integer cancelOrderByOrderId(Integer orderId) {
        Integer result = orderMapper.changeStatusByPrimaryKey(orderId, 2);
        return result;
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
                OrderBean temp = new OrderBean(result.get(i).getTypeId(), result.get(i).getOrderStatus(), result.get(i).getSum(), getCurrentUser(result.get(i).getSpecialistId()).getRealName());
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
                OrderBean temp = new OrderBean(result.get(i).getTypeId(), result.get(i).getOrderStatus(), result.get(i).getSum(), getCurrentUser(result.get(i).getPatientId()).getRealName());
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
        return orderMapper.insertSelective(order);
    }
}
