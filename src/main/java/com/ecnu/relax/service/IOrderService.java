package com.ecnu.relax.service;

import com.ecnu.relax.dto.OrderBean;
import org.aspectj.weaver.ast.Or;

import java.util.Date;
import java.util.List;

public interface IOrderService extends BaseService {
    List<OrderBean> getOrdersByUser(Integer userID, Integer startNo, Integer size);
    List<OrderBean> getOrdersBySpecialist(Integer specialistId, Integer startNo, Integer size);
    OrderBean getOrderDetailByOrderId(Integer orderId);
    Integer cancelOrderByOrderId(Integer orderId);
    Integer continueOrderByOrderId(Integer orderId);
    Integer submitCommentByOrder(Integer orderId,Double rating,String comment);
    Integer placeNewOrder(int userId, int specialistId, int typeId, double sum, String description, Date start, Date end);
    public int changePreOrderStatusBySpecialistId(int specialistId, Date startTime, Date endTime, Integer isOrdered, Integer isFree);
}
