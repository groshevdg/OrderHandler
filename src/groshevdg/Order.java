package groshevdg;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Order {
    private String name;
    private Date date;

    public Order(String name, Date date) {
        this.name = name;
        this.date = date;
    }

    @Override
    public String toString() {
        return "Name: " + name + ", date: " + date;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }
}

