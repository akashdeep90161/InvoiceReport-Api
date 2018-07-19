package com.walkover.user.api.resources.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.walkover.user.api.dao.model.Item;
import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.resources.commons.BaseResource;
import com.walkover.user.api.utils.commons.JsonViews;

public class ItemResource extends BaseResource<Item> {
    public ItemResource() {
        super(new Item());
    }

    public ItemResource(Item item) {

        super(item);
    }
   // public ItemResource(long id, String itemName, int quantity,String price) {
     //   super(new Item(id, itemName,quantity,price));
    //}
    public void setItemName(String itemName) {
        getModel().setItemName(itemName);
    }
    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public String getItemName(){ return getModel().getItemName();}

    public void setPrice(String price) { getModel().setPrice(price); }

    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public String getPrice(){ return getModel().getPrice();}

    public void setId(long itemId) { getModel().setItemId(itemId); }

    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public long getId(){ return getModel().getItemId();}

    public void setUser(User user) { getModel().setUser(user); }

    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public User getUser(){ return getModel().getUser();}
}
