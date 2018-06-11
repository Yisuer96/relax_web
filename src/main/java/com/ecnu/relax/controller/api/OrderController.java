package com.ecnu.relax.controller.api;

import com.ecnu.relax.dto.BaseJson;
import com.ecnu.relax.dto.OrderBean;
import com.ecnu.relax.model.Order;
import com.ecnu.relax.service.IOrderService;
import com.ecnu.relax.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController extends APIBaseController {
    @Autowired
    private IOrderService orderService;

    /**
     * @param userId
     * @param page   第几页，以0开始
     * @return 一组Order
     */
    @RequestMapping(value = "/getOrdersByPage", method = RequestMethod.GET)
    public List<OrderBean> getOrdersByPage(@RequestParam("userId") String userId, @RequestParam("identity") String identity, @RequestParam("page") String page) {
        List<OrderBean> resultOrders = new ArrayList<>();
        if (Integer.parseInt(identity) == 0)
            resultOrders = orderService.getOrdersByUser(Integer.parseInt(userId), Integer.parseInt(page) * 5, 5);
        else if (Integer.parseInt(identity) == 1)
            resultOrders = orderService.getOrdersBySpecialist(Integer.parseInt(userId), Integer.parseInt(page) * 5, 5);
        return resultOrders;
    }

    @RequestMapping(value = "/getOrderDetail", method = RequestMethod.GET)
    public OrderBean getOrderDetail(@RequestParam("orderId") String orderId) {
        OrderBean resultOrder = orderService.getOrderDetailByOrderId(Integer.parseInt(orderId));
        return resultOrder;
    }

    @RequestMapping(value = "/cancelOrder", method = RequestMethod.GET)
    public Integer cancelOrder(@RequestParam("orderId") String orderId) {
        Integer result = orderService.cancelOrderByOrderId(Integer.parseInt(orderId));
        return result;
    }

    @RequestMapping(value = "/continueOrder", method = RequestMethod.GET)
    public Integer continueOrder(@RequestParam("orderId") String orderId) {
        Integer result = orderService.continueOrderByOrderId(Integer.parseInt(orderId));
        return result;
    }

    @RequestMapping(value = "/submitComment", method = RequestMethod.GET)
    public Integer submitComment(@RequestParam("orderId") String orderId,@RequestParam("rating") String rating,@RequestParam("comment") String comment) {
        Integer result = orderService.submitCommentByOrder(Integer.parseInt(orderId),Double.parseDouble(rating),comment);
        return result;
    }

    @RequestMapping(value = "/placeOrder", method = RequestMethod.GET)
    public Integer placeOrder(@RequestParam("userId") String userId,@RequestParam("specialistId") String specialistId,
                              @RequestParam("selectedType") String selectedType,@RequestParam("money") String money,
                              @RequestParam("description") String description,@RequestParam("startTime") String startTime,
                              @RequestParam("endTime") String endTime) {
        Date start = new Date();
        Date end = new Date();
        start.setTime(Long.parseLong(startTime));
        end.setTime(Long.parseLong(endTime));
        Integer result = orderService.placeNewOrder(Integer.parseInt(userId),Integer.parseInt(specialistId),Integer.parseInt(selectedType),
                Double.parseDouble(money),description,start,end);
        return result;
    }
}