package com.design.patterns.demo.designpattern.create.singleton.v1;


/**
 * OrderController类
 *
 * @author wangjixue
 * @date 6/19/22 10:50 PM
 */
public class OrderController {
    private Logger logger;

    private void create(Order order){
        logger.log("create an order:"+ order.toString());
    }
}

class Order {

}
