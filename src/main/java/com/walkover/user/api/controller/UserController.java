/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.walkover.user.api.controller;

import com.walkover.user.api.dao.model.User;
import com.walkover.user.api.exception.AlreadyExistException;
import com.walkover.user.api.exception.InvalidRequestException;
import com.walkover.user.api.models.commens.ApiResponse;
import com.walkover.user.api.models.commens.ApiStatus;
import com.walkover.user.api.resources.v1.UserResource;
import com.walkover.user.api.services.v1.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

/**
 * @author Akash Deep Gupta
 **/

@Controller
@RequestMapping("/users")
public class UserController {

   private final UserService userservice;

    public UserController(UserService userservice) {
        this.userservice = userservice;
    }

    @RequestMapping(method = POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> createUser(@RequestBody UserResource userResource, HttpServletRequest request) throws AlreadyExistException, InvalidRequestException, ConstraintViolationException {
        User user = userservice.create(userResource.getModel());
        return new ResponseEntity<>(new ApiResponse(new UserResource(user), ApiStatus.success), HttpStatus.CREATED);
    }


    @RequestMapping(method = PUT)
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UserResource userResource, HttpServletRequest request) throws Exception {
        User user = userservice.update(userResource.getModel());
        return new ResponseEntity<>(new ApiResponse(user, ApiStatus.success), HttpStatus.OK);
    }

    @RequestMapping(method = GET, value = "/{emailId:.+}")
    public ResponseEntity<ApiResponse> getUser(@PathVariable String emailId, HttpServletRequest request) throws Exception {
        User user = userservice.findUser(emailId);
        return new ResponseEntity<>(new ApiResponse(user, ApiStatus.success), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<ApiResponse> getAllUser(HttpServletRequest request) throws Exception {
        List<User> all_user = userservice.findAllUser();
        return new ResponseEntity<>(new ApiResponse(all_user, ApiStatus.success), HttpStatus.OK);
    }

    @RequestMapping(method = DELETE, value = "/{emailId:.+}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable String emailId,
                                                  HttpServletRequest request) throws Exception {
        User user = userservice.findUser(emailId);
        userservice.deleteUser(user);
        return new ResponseEntity<>(new ApiResponse(user, ApiStatus.success), HttpStatus.OK);
    }

}
