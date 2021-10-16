package com.ecommerce.app.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecommerce.app.models.Order;
import com.ecommerce.app.models.OrderItem;

import java.util.List;

public class OrderResource  {
    @JsonProperty
    public long id;
    public String name;
    public String address;
    public String city;
    public String zip;
    public String status;
    public String comment;
    public String totalPrice;
    public String type;
    public String created;
    public List<OrderItem> items;

    public OrderResource(Order o){
        id = o.getId();
        name = o.getName();
        address = o.getAddress();
        city = o.getCity();
        zip = o.getZip();
        status = o.getStatus();
        comment = o.getComment();
        totalPrice = o.getTotalPrice();
        type = o.getType();
        created = o.getCreated();
        items = o.getItems();
    }
}
