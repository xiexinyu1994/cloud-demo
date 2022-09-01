package cn.itcast.order.service;

import cn.itcast.feign.client.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

//    @Autowired
//    RestTemplate restTemplate;

    @Autowired
    UserClient userClient;

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);

        // 2. 利用 RestTemplate 发起远程调用，get请求用get，post 请求用post
        // 2.1 url路径
//        String url = "http://userService/user/" + order.getUserId();
        // 2.2 发送http请求，实现远程调用
//        User user = restTemplate.getForObject(url, User.class);

        // 利用Fegin发起远程调用
        User user = userClient.findUserById(order.getUserId());
        // 封装user到order中
        order.setUser(user);
        // 4.返回
        return order;
    }
}
