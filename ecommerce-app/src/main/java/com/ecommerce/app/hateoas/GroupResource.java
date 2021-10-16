package com.ecommerce.app.hateoas;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ecommerce.app.models.GroupVariant;
import com.ecommerce.app.models.ProductGroup;

import java.util.List;

public class GroupResource {
    @JsonProperty
    public long id;
    public String groupName;
    public String price;
    public List<GroupVariant> variants;

    public GroupResource(ProductGroup group){
        this.id = group.getId();
        this.groupName = group.getGroupName();
        this.price = group.getPrice();
        this.variants = group.getGroupVariants();
    }
}
