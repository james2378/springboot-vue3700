package com.project.demo.controller;

import com.project.demo.entity.OrderCenter;
import com.project.demo.service.OrderCenterService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *订单中心：(OrderCenter)表控制层
 *
 */
@RestController
@RequestMapping("/order_center")
public class OrderCenterController extends BaseController<OrderCenter,OrderCenterService> {

    /**
     *订单中心对象
     */
    @Autowired
    public OrderCenterController(OrderCenterService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        String sql = "SELECT MAX(order_center_id) AS max FROM "+"order_center";
        Query select = service.runCountSql(sql);
        Integer max = (Integer) select.getSingleResult();
        sql = ("SELECT count(*) count FROM `cat_products` INNER JOIN `order_center` ON cat_products.commodity_number=order_center.commodity_number WHERE cat_products.merchandise_inventory &#60; order_center.purchase_quantity AND order_center.order_center_id="+max).replaceAll("&#60;","<");
        select = service.runCountSql(sql);
        Integer count = Integer.valueOf(String.valueOf(select.getSingleResult()));
        if(count>0){
            sql = "delete from "+"order_center"+" WHERE "+"order_center_id"+" ="+max;
            select = service.runCountSql(sql);
            select.executeUpdate();
            return error(30000,"库存不足!");
        }
        sql = "UPDATE `cat_products` INNER JOIN `order_center` ON cat_products.commodity_number=order_center.commodity_number SET cat_products.merchandise_inventory = cat_products.merchandise_inventory - order_center.purchase_quantity WHERE order_center.order_center_id="+max;
        select = service.runCountSql(sql);
        select.executeUpdate();
        return success(1);
    }

}
