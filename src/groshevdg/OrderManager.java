package groshevdg;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class OrderManager {
    private static OrderManager manager;
    private static Map<Integer, OrderHandler> handlers = new HashMap<>();
    private static final int HANDLER_NUMBERS = 3;

    private OrderManager() { }

    public static OrderManager getInstance() {
        if (manager == null) {
            manager = new OrderManager();

            handlers.put(0, new firstHandler());
            handlers.put(1, new secondHandler());
            handlers.put(2, new thirdHandler());
        }
        return manager;
    }

    public void handleOrders(Queue<Order> orders) {
        int handlerNumber = 0;

        while (orders.size() != 0) {

            Order order = orders.remove();
            handlers.get(handlerNumber).handle(order);
            handlerNumber++;

            if (handlerNumber == HANDLER_NUMBERS) {
                handlerNumber = 0;
            }
        }
    }



    private static class firstHandler implements OrderHandler {

        @Override
        public void handle(Order order) {
            System.out.println("It's working the first handler");
            String hash = convertToMD5(order);
            System.out.println("MD5 Hash: " + hash);
        }

        private String convertToMD5(Order order) {
            String hash = null;
            try {
                byte[] digest = MessageDigest.getInstance("MD5").digest(order.toString().getBytes("UTF-8"));
                BigInteger bigInt = new BigInteger(1, digest);
                hash = bigInt.toString(16);
                while (hash.length() < 32){
                    hash = "0"+ hash;
                }
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return hash;
        }
    }

    private static class secondHandler implements OrderHandler {

    private static List<String> specialOrders = new ArrayList<>();
    static {
        specialOrders.add("q");
        specialOrders.add("w");
        specialOrders.add("e");
        specialOrders.add("r");
        specialOrders.add("t");
        specialOrders.add("a");
        specialOrders.add("s");
        specialOrders.add("d");
        specialOrders.add("f");
        specialOrders.add("g");
    }

        @Override
        public void handle(Order order) {
            System.out.println("\nIt's working the second handler");
            String value = specialOrders.contains(order.getName()) ? "special" : "usual";
            System.out.println("This is " + value + " order");
        }
    }

    private static class thirdHandler implements OrderHandler {

        @Override
        public void handle(Order order) {
            System.out.println("\nIt's working the third handler");
            System.out.println(order.getName().replaceAll("[aoe]", "") + "\n");
        }
    }
}
