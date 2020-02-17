package groshevdg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Date;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        Queue<Order> orders = getOrders();
        OrderManager manager = OrderManager.getInstance();
        manager.handleOrders(orders);
    }

    private static Queue<Order> getOrders() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Queue<Order> orders = new ArrayDeque<>();
        String orderName;

        try {
            while (!(orderName = reader.readLine()).equals("")) {
                Order order = new Order(orderName, new Date());
                orders.add(order);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
