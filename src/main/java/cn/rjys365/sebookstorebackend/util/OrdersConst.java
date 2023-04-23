//Constants for orders. Will be deleted after JPA/JDBC implementation.

package cn.rjys365.sebookstorebackend.util;

import cn.rjys365.sebookstorebackend.datatypes.Order;
import cn.rjys365.sebookstorebackend.datatypes.OrderItem;

import java.util.ArrayList;

public class OrdersConst {
    public static final Order[] orders={
            new Order(1,new ArrayList<OrderItem>(){
                {
                    add(new OrderItem(1,1,20.0,"一九八四"));
                    add(new OrderItem(2,2,30.0,"红楼梦"));
                }
            }),
            new Order(2,new ArrayList<OrderItem>(){
                {
                    add(new OrderItem(3,3,25.0,"三国演义"));
                    add(new OrderItem(1,2,20.0,"一九八四"));
                    add(new OrderItem(4,1,20.0,"深入理解计算机系统"));
                }
            }),
    };
}
