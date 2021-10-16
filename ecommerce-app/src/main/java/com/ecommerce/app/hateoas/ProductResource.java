package com.ecommerce.app.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecommerce.app.models.Product;

public class ProductResource  {

    @JsonProperty
    public long id;
    public String name;
    public String price;
    public String description;
    public Object group;

    public ProductResource(Product model){
        id = model.getId();
        name = model.getName();
        price = model.getPrice();
        description = model.getDescription();
        group = model.getGroup();
    }

}
