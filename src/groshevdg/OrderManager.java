package groshevdg;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class OrderManager {
    private static Map<Integer, OrderHandler> handlers = new HashMap<>();
    static {
        handlers.put(0, new FirstHandler());
        handlers.put(1, new SecondHandler());
        handlers.put(2, new ThirdHandler());
    }

    public void handleOrders(Queue<Order> orders) {
        int handlerNumber = 0;

        while (orders.size() != 0) {

            Order order = orders.poll();
            handlers.get(handlerNumber).handle(order);
            handlerNumber++;

            if (handlerNumber == handlers.size()) {
                handlerNumber = 0;
            }
        }
    }


    private static class FirstHandler implements OrderHandler {

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
                    hash = "0" + hash;
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

    private static class SecondHandler implements OrderHandler {

    private static Set<String> specialOrders = new HashSet<>();
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

    private static class ThirdHandler implements OrderHandler {

        @Override
        public void handle(Order order) {
            System.out.println("\nIt's working the third handler");
            System.out.println(order.getName().replaceAll("[aoe]", "") + "\n");
        }
    }
}
