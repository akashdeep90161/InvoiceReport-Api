/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.resources.v1;

import com.fasterxml.jackson.annotation.JsonView;
import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.resources.commons.BaseResource;
import com.walkover.user.api.utils.commons.JsonViews;

/**
 * @author Akash Deep Gupta
 */
public class UserResource extends BaseResource<User> {

    public UserResource() {

        super(new User());
    }

    public UserResource(User user) {
        super(user);
    }

   // public UserResource(long id, String emailId, String name) {
     //   super(new User(id, emailId, name));
   // }

    public void setEmailId(String emailId) {
        getModel().setEmailId(emailId);
    }

    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public String getEmailId() {
        return getModel().getEmailId();
    }

    public void setName(String name) {
        getModel().setName(name);
    }

    @JsonView(JsonViews.userDetailsExcludingPassword.class)
    public String getName() {

        return getModel().getName();
    }

}
